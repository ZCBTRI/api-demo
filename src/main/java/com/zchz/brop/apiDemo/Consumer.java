package com.zchz.brop.apiDemo;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static String createTxInfo = null;

    static String createCreateInfo = null;

    static String updateTxInfo = null;

    static String updateUpdateInfo = null;

    static String createHash = null;

    static String updateHash = null;

    public static void main(String[] args) throws MQClientException {

        //写入存证合约
        createHash = createRecord();
        //修改存证合约
        updateHash = updateRecord();
        //通过消息队列捕获上链结果
        getCreateResult();
        logger.info("mq return info:{},{},{},{}", createTxInfo, createCreateInfo, updateTxInfo, updateUpdateInfo);
    }

    // 存证代理合约的合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;
    // 存证合约的合约地址
    private static final String registryAddress = ApplicationConfig.recordRegistryAddress;
    // 存证流水号，必填，每次写入都需要更新，存证流水号必须唯一，重复提交会失败
    public static String seqId;
    // 前序存证流水号，可为空
    public static final String preSeqId = "";
    // 存证信息
    public static String recordData = "dc4ca97bb8aff0dc47655acb75f7f3e2d8e1f03d";
    // 自定义字符串
    public static final String customString = "asdf";
    // 当前操作人身份合约地址
    public static final String opIdentityAddress = ApplicationConfig.coopAddress;

    public static String createRecord(){
        seqId = "test" + System.currentTimeMillis();
        final Integer blockLimit = BROPUtils.blockNumber() + 50;
        final String bizId = Calendar.getInstance().getTimeInMillis() + "";
        final String method = "createRecord";
        final Object[] params = {
                registryAddress,
                seqId,
                preSeqId,
                recordData,
                customString,
                opIdentityAddress
        };
        // 获取待签原文
        String deployInfo = BROPUtils.buildDeployInfo(blockLimit.toString(), bizId, method, params, contractAddress);
        // 签名
        String signInfo = BROPUtils.sign(deployInfo, ApplicationConfig.hsmName);
        // 发送交易
        String txHash = BROPUtils.sendTx(blockLimit.toString(), bizId, method, params, signInfo, contractAddress);
        logger.info("txHash: {}", txHash);
        return txHash;
    }

    public static String updateRecord(){
        final Integer blockLimit = BROPUtils.blockNumber() + 50;
        final String bizId = Calendar.getInstance().getTimeInMillis() + "";
        final String method = "updateRecord";
        final Object[] params = {
                registryAddress,
                seqId,
                preSeqId,
                recordData,
                customString,
                opIdentityAddress
        };
        // 获取待签原文
        String deployInfo = BROPUtils.buildDeployInfo(blockLimit.toString(), bizId, method, params, contractAddress);
        // 签名
        String signInfo = BROPUtils.sign(deployInfo, ApplicationConfig.hsmName);
        // 发送交易
        String txHash = BROPUtils.sendTx(blockLimit.toString(), bizId, method, params, signInfo, contractAddress);
        logger.info("txHash: {}", txHash);
        return txHash;
    }

    public static void getCreateResult() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mq-demo"); // 创建消费者，定义消费者名称
        consumer.setNamesrvAddr(ApplicationConfig.rocketAddress); //MQnamesrv
        consumer.setInstanceName(Long.toString(System.currentTimeMillis()));
        consumer.subscribe("Topic_zchz_brop", "EVENT_BLOCKNUMBER||EVENT_TX_RECORDED||EVENT_CONTRACT_CREATE||EVENT_CONTRACT_UPDATED");
        consumer.setConsumeTimeout(1);
        consumer.registerMessageListener(new MessageListenerOrderly(){

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for(MessageExt msg : msgs){

                    //捕获交易消息
                    if(msg.getTags() != null && msg.getTags().equals("EVENT_TX_RECORDED")){
                        //消费EVENT_TX_RECORDED
                        byte[] bytes = msg.getBody();
                        try {
                            String info = new String(bytes,"UTF-8");
                            Map<String, Object> content = mapper.readValue(info, Map.class);
                            if(content.get("txHash").equals(createHash)){
                                logger.info("get info :{}", info);
                                createTxInfo = info;
                            }
                            if(content.get("txHash").equals(updateHash)){
                                logger.info("get info :{}", info);
                                updateTxInfo = info;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // 捕获合约创建交易
                    if(msg.getTags() != null && msg.getTags().equals("EVENT_CONTRACT_CREATE")){
                        //消费EVENT_CONTRACT_CREATE
                        byte[] bytes = msg.getBody();
                        createCreateInfo = getInfoByHash(bytes, createHash);
                    }
                    if(msg.getTags() != null && msg.getTags().equals("EVENT_CONTRACT_UPDATED")){
                        //消费EVENT_CONTRACT_UPDATED
                        byte[] bytes = msg.getBody();
                        updateUpdateInfo = getInfoByHash(bytes, updateHash);
                    }
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }

            private String getInfoByHash(byte[] bytes, String createHash) {
                try {
                    String info = new String(bytes, "UTF-8");
                    Map<String, Object> content = mapper.readValue(info, Map.class);
                    if (content.get("txHash").equals(createHash)) {
                        logger.info("get info :{}", info);
                        return info;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        consumer.start();
        while (true){
            logger.info("mq return info:{},{},{},{}", createTxInfo, createCreateInfo, updateTxInfo, updateUpdateInfo);
            if(createTxInfo!=null && createCreateInfo!=null && updateTxInfo!=null && updateUpdateInfo!=null){
                consumer.shutdown();
                return ;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
