package com.zchz.brop.apiDemo.ntsc;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.enums.BROPContractResultcode;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.TransactionResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 授时存证合约
 * 设置是否使用白名单规则，管理该授时存证合约的合作方身份合约可以调用。
 */
public class SetAuthListType {
    private static final Logger logger = LoggerFactory.getLogger(SetAuthListType.class);
    // 授时存证代理合约地址
    private static final String contractAddress = ApplicationConfig.registryNTSCProxy;
    // 授时存证合约的合约地址
    private static final String recordRegistryAddress = ApplicationConfig.registryNTSCAddress;
    // 是否使用白名单，默认是"true"，如果设置成"false"，则使用黑名单规则
    public static final Boolean role = true;

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
        final Integer blockLimit = BROPUtils.blockNumber() + 50;
        final String bizId = Calendar.getInstance().getTimeInMillis() + "";
        final String method = "setAuthListType";
        final Object[] params = {recordRegistryAddress, role};
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
                    logger.info("txMessage: {}", transactionResult.toString());
                    logger.info((role == true) ? "设置使用白名单规则成功" : "设置使用黑名单规则成功");
                    break;
                } else {
                    String errorMessage = BROPContractResultcode.getCodeMessage(transactionResult.getResultCode());
                    logger.info((role == true) ? "设置使用白名单规则失败" : "设置使用黑名单规则失败");
                    logger.info("失败原因：{}", errorMessage);
                    break;
                }
            }
            sleep(3000);
            i++;
        }
        if (i >= 10) {
            logger.info((role == true) ? "设置使用白名单规则失败" : "设置使用黑名单规则失败");
        }
    }
}
