package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.enums.BROPContractResultcode;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.TransactionResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import com.zchz.brop.apiDemo.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 写入存证记录,拥有当前存证合约的写入存证记录权限的身份合约用户可以调用。
 *
 * @author zccp
 */
public class CreateRecord {
    private static final Logger logger = LoggerFactory.getLogger(CreateRecord.class);
    // 存证代理合约的合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;
    // 存证合约的合约地址
    private static final String registryAddress = ApplicationConfig.recordRegistryAddress;
    // 存证流水号，必填，每次写入都需要更新，存证流水号必须唯一，重复提交会失败
    public static String seqId;
    // 前序存证流水号，可为空
    public static final String preSeqId = "";
    // 存证信息
    public static String recordData;

    static {
        try {
            recordData = EncryptionUtil.encryptStr("1ca6574c4dd8749538798af8ac9cb08412d5036f45b338062a1db4ef8fb5008a");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    // 自定义字符串
    public static final String customString = "asdf";
    // 当前操作人身份合约地址
    public static final String opIdentityAddress = ApplicationConfig.coopAddress;

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
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
        // 轮询校验交易结果
        int i = 0;
        while (i < 10) {
            CommonResult<Map<String, TransactionResult>> commonResult = BROPUtils.getTxMessage(txHash);
            TransactionResult transactionResult;
            if (commonResult.getDetail().get("transaction") != null) {
                transactionResult =
                        commonResult.getDetail().get("transaction");
                if (transactionResult.getResultCode() == BROPContractResultcode.CODE_SUCCESS.getStatus()) {
                    logger.info("txMessage: {} \n 写入存证记录成功", transactionResult.toString());
                    break;
                } else {
                    String errorMessage = BROPContractResultcode.getCodeMessage(transactionResult.getResultCode());
                    logger.info("写入存证记录失败,失败原因：{}", errorMessage);
                    break;
                }
            }
            sleep(3000);
            i++;
        }
        if (i >= 10) {
            logger.info("写入存证记录失败");
        }
    }
}
