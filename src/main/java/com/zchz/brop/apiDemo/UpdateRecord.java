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
 * 根据存证流水号修改存证记录
 */
public class UpdateRecord {
    private static final Logger logger = LoggerFactory.getLogger(CreateRecord.class);
    // 存证代理合约的合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;
    // 存证合约的合约地址
    private static final String registryAddress = ApplicationConfig.recordRegistryAddress;
    // 存证流水号，必填
    public static final String seqId = "test1527065921452";
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
    public static final String customString = "abcdefg";
    // 当前操作人身份合约地址
    public static final String opIdentityAddress = ApplicationConfig.coopAddress;

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
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
        // 轮询校验交易结果
        int i = 0;
        while (i < 10) {
            CommonResult<Map<String, TransactionResult>> commonResult = BROPUtils.getTxMessage(txHash);
            TransactionResult transactionResult;
            if (commonResult.getDetail().get("transaction") != null) {
                transactionResult =
                        commonResult.getDetail().get("transaction");
                if (transactionResult.getResultCode() == BROPContractResultcode.CODE_SUCCESS.getStatus()) {
                    logger.info("txMessage: {} \n 修改存证记录成功", transactionResult.toString());
                    break;
                } else {
                    String errorMessage = BROPContractResultcode.getCodeMessage(transactionResult.getResultCode());
                    logger.info("修改存证记录失败,失败原因：{}", errorMessage);
                    break;
                }
            }
            sleep(3000);
            i++;
        }
        if (i >= 10) {
            logger.info("修改存证记录失败");
        }
    }
}
