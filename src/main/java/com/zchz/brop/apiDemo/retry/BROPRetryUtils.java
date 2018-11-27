package com.zchz.brop.apiDemo.retry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zchz.brop.apiDemo.res.RetryTxResult;
import com.zchz.brop.apiDemo.utils.HttpClientSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.zchz.brop.apiDemo.config.ApplicationConfig.middleware;

public class BROPRetryUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BROPRetryUtils.class);

    /**
     * 普通交易
     * @param contractAddress 合约地址
     * @param bizId 业务ID
     * @param blockLimit 超时块号
     * @param method 合约方法
     * @param params 合约构造函数参数
     * @param hsmUserId 加密机用户索引
     * @return 唯一交易业务编号
     */
    public static String sendTx(String contractAddress,
                                 String bizId,
                                 String blockLimit,
                                 String method,
                                 Object[] params,
                                 String hsmUserId) {
        JSONObject content = new JSONObject();
        content.put("contractAddress", contractAddress);
        content.put("bizId", bizId);
        content.put("blockLimit", blockLimit);
        content.put("method", method);
        content.put("params", params);
        content.put("hsmUserId", hsmUserId);

        final String url = middleware + "/transaction/sendTransaction.json";
        LOGGER.info("invoke url:{},body:{}", url, content);
        try {

            JSONObject result = JSON.parseObject(HttpClientSevice.postJsonObject(url, content));
            if ("SUCCESS".equals(result.getString("code"))) {
                return result.getString("detail");
            } else {
                LOGGER.error("invoke error,result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("invoke error,throws ", e);
        }

        return null;
    }

    /**
     * 普通交易（失败重试机制）
     * @param contractAddress 合约地址
     * @param bizId 业务ID
     * @param method 合约方法
     * @param params 合约构造函数参数
     * @param hsmUserId 加密机用户索引
     * @return 唯一交易业务编号
     */
    public static String sendTxWithRetry(String contractAddress,
                                            String bizId,
                                            String method,
                                            Object[] params,
                                            String hsmUserId) {
        JSONObject content = new JSONObject();
        content.put("contractAddress", contractAddress);
        content.put("bizId", bizId);
        content.put("method", method);
        content.put("params", params);
        content.put("hsmUserId", hsmUserId);

        final String url = middleware + "/autotxretry/sendTxAndRetry.json";
        LOGGER.info("invoke url:{},body:{}", url, content);
        try {

            JSONObject result = JSON.parseObject(HttpClientSevice.postJsonObject(url, content));
            if ("SUCCESS".equals(result.getString("code"))) {
                return result.getString("detail");
            } else {
                LOGGER.error("invoke error,result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("invoke error,throws ", e);
        }

        return null;
    }

    /**
     * 授时交易（失败重试机制）
     * @param contractAddress 合约地址
     * @param bizId 业务ID
     * @param method 合约方法
     * @param params 合约构造函数参数
     * @param hsmUserId 加密机用户索引
     * @return 唯一交易业务编号
     */
    public static String sendNtscTxWithRetry(String contractAddress,
                                                String bizId,
                                                String method,
                                                Object[] params,
                                                String hsmUserId) {
        JSONObject content = new JSONObject();
        content.put("contractAddress", contractAddress);
        content.put("bizId", bizId);
        content.put("method", method);
        content.put("params", params);
        content.put("hsmUserId", hsmUserId);

        final String url = middleware + "/autotxretry/sendNTSCTxAndRetry.json";
        LOGGER.info("invoke url:{},body:{}", url, content);
        try {

            JSONObject result = JSON.parseObject(HttpClientSevice.postJsonObject(url, content));
            if ("SUCCESS".equals(result.getString("code"))) {
                return result.getString("detail");
            } else {
                LOGGER.error("invoke error,result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("invoke error,throws ", e);
        }

        return null;
    }

    /**
     * 查询有失败重试机制的交易的上链情况
     * @param flowNumber 业务流水号
     * @return
     */
    public static RetryTxResult queryTxWithRetryResult(String flowNumber) {
        StringBuilder url = new StringBuilder(middleware).append("/autotxretry/queryResult.json?flowNumber=").append(flowNumber);
        LOGGER.info("invoke url:{}", url.toString());
        try {
            JSONObject result = JSON.parseObject(HttpClientSevice.get(url.toString()));
            if ("SUCCESS".equals(result.getString("code"))) {
                return result.getObject("detail", RetryTxResult.class);
            } else {
                LOGGER.error("invoke error,result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("invoke error,throws ", e);
        }

        return null;
    }

    /**
     * 重启"无效的"交易
     * @param flowNumber 业务流水号（若为null，则全部重置）
     * @return 操作成功返回true
     */
    public static boolean resetInvalidTxWithRetry(String flowNumber) {
        StringBuilder url = new StringBuilder(middleware).append("/autotxretry/resetInvalidRetry.json");
        if (flowNumber != null) {
            url.append("?flowNumber=").append(flowNumber);
        }
        LOGGER.info("invoke url:{}", url.toString());
        try {
            JSONObject result = JSON.parseObject(HttpClientSevice.get(url.toString()));
            if ("SUCCESS".equals(result.getString("code"))) {
                return true;
            } else {
                LOGGER.error("invoke error,result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("invoke error,throws ", e);
        }

        return false;
    }

    /**
     * 重启"交易处理中的"交易
     * @param flowNumber 业务流水号（若为null，则全部重置）
     * @return 操作成功返回true
     */
    public static boolean resetUnfinishedTxWithRetry(String flowNumber) {
        StringBuilder url = new StringBuilder(middleware).append("/autotxretry/resetDotxRetry.json");
        if (flowNumber != null) {
            url.append("?flowNumber=").append(flowNumber);
        }
        LOGGER.info("invoke url:{}", url.toString());
        try {
            JSONObject result = JSON.parseObject(HttpClientSevice.get(url.toString()));
            if ("SUCCESS".equals(result.getString("code"))) {
                return true;
            } else {
                LOGGER.error("invoke error,result:{}", result);
            }
        } catch (Exception e) {
            LOGGER.error("invoke error,throws ", e);
        }

        return false;
    }

}