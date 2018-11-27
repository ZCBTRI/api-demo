package com.zchz.brop.apiDemo.composite;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.enums.BROPContractResultcode;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.TransactionResult;
import com.zchz.brop.apiDemo.retry.BROPRetryUtils;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 添加用户到黑名单
 */
public class AddUserToBlackList {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddUserToBlackList.class);

    // 存证代理合约的合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;

    // 存证合约的合约地址
    private static final String recordRegistryAddress = ApplicationConfig.recordRegistryAddress;

    // 要添加的身份合约的账户ID
    private static final String accountID = ApplicationConfig.coopAccountId;

    private static final String hsmUserId = ApplicationConfig.hsmName;

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
        final String blockLimit = BROPUtils.blockNumber() + 50 + "";
        final String bizId = Calendar.getInstance().getTimeInMillis() + "";
        final String method = "addUserToBlackList";

        // 发送交易
        String txHash = BROPRetryUtils.sendTx(contractAddress,
                                                bizId,
                                                blockLimit,
                                                method,
                                                new Object[]{recordRegistryAddress, accountID},
                                                hsmUserId);
        LOGGER.info("txHash: {}", txHash);
        // 轮询校验交易结果
        int i = 0;
        while (i < 10) {
            @SuppressWarnings("unchecked")
            CommonResult<Map<String, TransactionResult>> commonResult = BROPUtils.getTxMessage(txHash);
            TransactionResult transactionResult;
            if (commonResult.getDetail().get("transaction") != null) {
                transactionResult =
                        commonResult.getDetail().get("transaction");
                if (transactionResult.getResultCode() == BROPContractResultcode.CODE_SUCCESS.getStatus()) {
                    LOGGER.info("txMessage: {} \n 新增黑名单用户成功", transactionResult.toString());
                    break;
                } else {
                    String errorMessage = BROPContractResultcode.getCodeMessage(transactionResult.getResultCode());
                    LOGGER.info("新增黑名单用户失败,失败原因：{}", errorMessage);
                    break;
                }
            }
            sleep(3000);
            i++;
        }
        if (i >= 10) {
            LOGGER.info("新增黑名单用户失败");
        }
    }

}
