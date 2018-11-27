package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.enums.BROPContractResultcode;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.TransactionResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;

import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * 将人员从黑名单中删除
 */
public class RemoveUserFromBlackList {
    private static final org.slf4j.Logger logger = getLogger(RemoveUserFromBlackList.class);
    // 存证代理合约的合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;
    // 存证合约的合约地址
    private static final String recordRegistryAddress = ApplicationConfig.recordRegistryAddress;
    // 要删除的身份合约的账户ID
    public static final Integer accountID = Integer.parseInt(ApplicationConfig.coopAccountId);
    private Integer blockLimit;

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
        logger.info("将用户从黑名单移除");
        final Integer blockLimit = BROPUtils.blockNumber() + 50;
        final String bizId = Calendar.getInstance().getTimeInMillis() + "";
        final String method = "removeUserFromBlackList";
        final Object[] params = {recordRegistryAddress, accountID};
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
                    logger.info("txMessage: {} \n 删除黑名单用户成功", transactionResult.toString());
                    break;
                } else {
                    String errorMessage = BROPContractResultcode.getCodeMessage(transactionResult.getResultCode());
                    logger.info("删除黑名单用户失败,失败原因：{}", errorMessage);
                    break;
                }
            }
            sleep(3000);
            i++;
        }
        if (i >= 10) {
            logger.info("删除黑名单用户失败");
        }
    }
}
