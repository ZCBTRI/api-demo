package com.zchz.brop.apiDemo.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.req.*;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.NTSCRes;
import com.zchz.brop.apiDemo.res.TransactionResult;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Map;

import static com.zchz.brop.apiDemo.config.ApplicationConfig.middleware;

public class BROPUtils {
    private static final Logger logger = getLogger(BROPUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取当前块号
     *
     * @return
     */
    public static Integer blockNumber() {
        CommonResult commonResult = new CommonResult();
        String blockNumber = null;
        Integer currentBlockNumber = -1;
        try {
            logger.info("sender url: {}", middleware + "/common/blockNumber.json");
            blockNumber = HttpClientSevice.get(middleware + "/common/blockNumber.json");
            commonResult = JSON.parseObject(blockNumber, new com.alibaba.fastjson.TypeReference<CommonResult>() {
            });
            logger.info("sender res: {}", commonResult.toString());
            currentBlockNumber = (Integer) commonResult.getDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
        return currentBlockNumber;
    }

    /**
     * 获取当前同步块号
     *
     * @return
     */
    public static Integer blockNumberDB() {
        CommonResult commonResult = new CommonResult();
        String blockNumber = null;
        Integer currentBlockNumber = -1;
        try {
            logger.info("sender url: {}", middleware + "/common/blockNumberDB.json");
            blockNumber = HttpClientSevice.get(middleware + "/common/blockNumberDB.json");
            commonResult = JSON.parseObject(blockNumber, new com.alibaba.fastjson.TypeReference<CommonResult>() {
            });
            logger.info("sender res: {}", commonResult.toString());
            currentBlockNumber = (Integer) commonResult.getDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
        return currentBlockNumber;
    }

    /**
     * 获取块信息
     *
     * @return
     */
    public static CommonResult blockMsg(String blockNumberFrom,
                                        String blockNumberTo,
                                        String blockTimeFrom,
                                        String blockTimeTo) {
        BlockMsgReq blockMsgReq = new BlockMsgReq(
                blockNumberFrom,
                blockNumberTo,
                blockTimeFrom,
                blockTimeTo,
                ApplicationConfig.pageSize,
                ApplicationConfig.pageNum
        );
        CommonResult commonResult = new CommonResult();
        String result = null;
        try {
            logger.info("sender url: {}, body: {}", middleware + "/block/lite/search.json", blockMsgReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/block/lite/search.json", blockMsgReq);
            logger.info("sender res: {}", result);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult>() {
            });

        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 获取交易信息
     *
     * @param txHash        交易哈希
     * @param sender        发送人
     * @param bizId         标识字段
     * @param blockTimeFrom 块时间大于等于该值
     * @param blockTimeTo   块时间小于该值
     * @return
     */
    public static CommonResult txMsg(String txHash,
                                     String sender,
                                     String bizId,
                                     String blockTimeFrom,
                                     String blockTimeTo) {
        TxMsgReq txMsgReq = new TxMsgReq(
                txHash,
                sender,
                bizId,
                blockTimeFrom,
                blockTimeTo,
                ApplicationConfig.pageSize,
                ApplicationConfig.pageNum
        );
        CommonResult commonResult = new CommonResult();
        String result = null;
        try {
            logger.info("sender url: {}, body: {}", middleware + "/block/lite/searchByTransaction.json", txMsgReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/block/lite/searchByTransaction.json", txMsgReq);
            logger.info("sender res: {}", result);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult>() {
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 查询合约
     *
     * @param contract      合约地址，必填
     * @param blockNumber   块号
     * @param blockTimeFrom 块时间大于等于该值
     * @param blockTimeTo   块时间小于该值
     * @return
     */
    public static CommonResult searchContarct(String contract,
                                              int blockNumber,
                                              String blockTimeFrom,
                                              String blockTimeTo) {
        SearchByContarctReq searchByContarctReq = new SearchByContarctReq(
                contract,
                blockNumber,
                blockTimeFrom,
                blockTimeTo,
                ApplicationConfig.pageSize,
                ApplicationConfig.pageNum
        );
        CommonResult commonResult = new CommonResult();
        String result = null;
        try {
            logger.info("sender url: {}, body: {}", middleware + "/block/searchByContract.json", searchByContarctReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/block/searchByContract.json", searchByContarctReq);
            logger.info("sender res: {}", result);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult>() {
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 获取待签原文
     *
     * @param blockLimit
     * @param bizId
     * @param method
     * @param params
     * @return
     */
    public static String buildDeployInfo(String blockLimit,
                                         String bizId,
                                         String method,
                                         Object[] params,
                                         String dataUnitAddress) {
        BuildDeployInfoReq buildDeployInfoReq = getBuildDeployInfoReq(blockLimit, bizId, method, params, dataUnitAddress);
        CommonResult commonResult = new CommonResult();
        String deployInfo = null;
        String result = null;
        try {
            logger.info("sender url: {}, body: {}", middleware + "/common/buildSendInfo.json", buildDeployInfoReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/common/buildSendInfo.json", buildDeployInfoReq);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<String>>() {
            });
            logger.info("sender res: {}", commonResult.toString());
            deployInfo = (String) commonResult.getDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
        return deployInfo;
    }

    /**
     * 签名
     *
     * @param signReq
     * @return
     */
    public static String sign(String signReq, String hsmUserId) {
        String result = null;
        String signInfo = null;
        CommonResult commonResult = new CommonResult();
        try {
            logger.info("sender url: {}", ApplicationConfig.hsmServer + "/crypt/sign.json?userId=" + hsmUserId + "&messageHash=" + signReq);
            result = HttpClientSevice.get(ApplicationConfig.hsmServer + "/crypt/sign.json?userId=" + hsmUserId + "&messageHash=" + signReq);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<String>>() {
            });
            logger.info("sender res: {}", commonResult.toString());
            signInfo = (String) commonResult.getDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
        return signInfo;
    }

    /**
     * 发送交易
     *
     * @param blockLimit
     * @param bizId
     * @param method
     * @param params
     * @param signatureInBase64
     * @return
     */
    public static String sendTx(String blockLimit,
                                String bizId,
                                String method,
                                Object[] params,
                                String signatureInBase64,
                                String dataUnitAddress) {
        SendTxReq sendTxReq = getSendTxReq(blockLimit, bizId, method, params, signatureInBase64, dataUnitAddress);
        String result = null;
        String txHash = null;
        CommonResult commonResult = new CommonResult();
        try {
            logger.info("sender url: {}, body: {}", middleware + "/common/sendTx.json", sendTxReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/common/sendTx.json", sendTxReq);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<String>>() {
            });
            logger.info("sender res: {}", commonResult.toString());
            txHash = (String) commonResult.getDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
        return txHash;
    }

    /**
     * 获取授时合约待签原文
     *
     * @param contractAddress
     * @param bizId
     * @param blockLimit
     * @param method
     * @param params
     * @return
     */
    public static NTSCRes buildSendInfoByNTSC(String contractAddress,
                                     String bizId,
                                     String blockLimit,
                                     String method,
                                     Object[] params) {
        NTSCReq ntscReq = new NTSCReq(contractAddress, bizId, blockLimit, method, params);
        NTSCRes ntscRes;
        String result = null;
        String txHash = null;
        CommonResult<Map<String, Object>> commonResult = new CommonResult();
        try {
            logger.info("sender url: {}, body: {}", middleware + "/common/buildSendInfoByNTSC", ntscReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/common/buildSendInfoByNTSC", ntscReq);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<Map<String, Object>>>() {
            });
            logger.info("sender res: {}", commonResult.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        ntscRes = new NTSCRes(
                (String)commonResult.getDetail().get("rawText"),
                Long.parseLong((String) commonResult.getDetail().get("ntscTime")),
                (String)commonResult.getDetail().get("ntscSignature")
        );
        return ntscRes;
    }

    /**
     * 根据交易hash获取交易信息
     *
     * @param txHash
     * @return
     */
    public static CommonResult getTxMessage(String txHash) {
        String result = null;
        CommonResult commonResult = new CommonResult();
        try {
            logger.info("sender url: {}", middleware + "/transaction/" + txHash + ".json");
            result = HttpClientSevice.get(middleware + "/transaction/" + txHash + ".json");
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<Map<String, TransactionResult>>>() {
            });
            logger.info("sender res: {}", commonResult.toString());

        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 根据bizId查询交易
     *
     * @param bizId
     * @return
     */
    public static CommonResult getTxMessageByBizId(String bizId) {
        String result = null;
        CommonResult commonResult = new CommonResult();
        try {
            logger.info("sender url: {}", middleware + "/transaction/search.json?bizId=" + bizId);
            result = HttpClientSevice.get(middleware + "/transaction/search.json?bizId=" + bizId);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult>() {
            });
            logger.info("sender res: {}", commonResult.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 根据合约地址和块号获取合约
     *
     * @param contractAddress 合约地址,必填
     * @param blockNumber     块号,选填
     * @return
     */
    public static CommonResult getContract(String contractAddress,
                                           int blockNumber) {
        if (contractAddress == null || "".equals(contractAddress)) {
            logger.info("合约地址不能为空");
            return null;
        }
        String result = null;
        CommonResult commonResult = new CommonResult();
        try {
            logger.info("sender url: {}", middleware + "/contract/" + contractAddress + ".json?blockNumber=" + blockNumber);
            result = HttpClientSevice.get(middleware + "/contract/" + contractAddress + ".json?blockNumber=" + blockNumber);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult>() {
            });
            logger.info("sender res: {}", commonResult.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 只读接口获取信息
     *
     * @param contractAddress
     * @param functionName
     * @param params
     * @return
     */
    public static CommonResult contract(String contractAddress,
                                        String functionName,
                                        Object[] params) {
        ContractReq contractReq = getContractReq(contractAddress, functionName, params);
        String result = null;
        CommonResult commonResult = new CommonResult();
        try {
            logger.info("sender url: {}, body: {}", middleware + "/common/contract.json", contractReq.toString());
            result = HttpClientSevice.postJsonObject(middleware + "/common/contract.json", contractReq);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<Map<String, Object>>>() {
            });
            logger.info("sender res: {}", commonResult.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }

    /**
     * 根据加密机获取对应公钥地址
     *
     * @param userId
     * @return
     */
    public static CommonResult getPublicAddress(String userId) {
        String result = null;
        CommonResult commonResult = new CommonResult();
        try {
            result = HttpClientSevice.get(ApplicationConfig.hsmServer + "/crypt/generateKeyFromHsm.json?userId=" + userId);
            commonResult = mapper.readValue(result, new TypeReference<CommonResult<String>>() {
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return commonResult;
    }


    private static ContractReq getContractReq(String contractAddress, String functionName, Object[] params) {
        ContractReq contractReq = new ContractReq();
        contractReq.setContractAddress(contractAddress);
        contractReq.setFunctionName(functionName);
        contractReq.setParams(params);
        return contractReq;
    }

    private static SendTxReq getSendTxReq(String blockLimit, String bizId, String method, Object[] params, String signatureInBase64, String dataUnitAddress) {
        SendTxReq sendTxReq = new SendTxReq();
        sendTxReq.setBlockLimit(blockLimit);
        sendTxReq.setBizId(bizId);
        sendTxReq.setContractAddress(dataUnitAddress);
        sendTxReq.setMethod(method);
        sendTxReq.setParams(params);
        sendTxReq.setSignatureInBase64(signatureInBase64);
        return sendTxReq;
    }

    private static BuildDeployInfoReq getBuildDeployInfoReq(String blockLimit, String bizId, String method, Object[] params, String dataUnitAddress) {
        BuildDeployInfoReq buildDeployInfoReq = new BuildDeployInfoReq();
        buildDeployInfoReq.setBlockLimit(blockLimit);
        buildDeployInfoReq.setBizId(bizId);
        buildDeployInfoReq.setContractAddress(dataUnitAddress);
        buildDeployInfoReq.setMethod(method);
        buildDeployInfoReq.setParams(params);
        return buildDeployInfoReq;
    }
}