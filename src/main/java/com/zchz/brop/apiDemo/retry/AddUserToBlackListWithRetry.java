package com.zchz.brop.apiDemo.retry;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.enums.BROPContractResultcode;
import com.zchz.brop.apiDemo.enums.RetryTxState;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.RetryTxResult;
import com.zchz.brop.apiDemo.res.TransactionResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 添加用户到黑名单
 */
public class AddUserToBlackListWithRetry {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddUserToBlackListWithRetry.class);

    // 存证代理合约的合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;

    // 存证合约的合约地址
    private static final String recordRegistryAddress = ApplicationConfig.recordRegistryAddress;

    // 要添加的身份合约的账户ID
    private static final String accountID = ApplicationConfig.coopAccountId;

    private static final String hsmUserId = ApplicationConfig.hsmName;

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
        final String bizId = Calendar.getInstance().getTimeInMillis() + "";
        final String method = "addUserToBlackList";

        // 发送交易
        String flowNumber = BROPRetryUtils.sendTxWithRetry(contractAddress,
                                                            bizId,
                                                            method,
                                                            new Object[] {recordRegistryAddress, accountID},
                                                            hsmUserId);

        LOGGER.info("flowNumber: {}", flowNumber);
        if (flowNumber == null) {
            LOGGER.error("新增黑名单用户失败,调用中间件失败");
            return;
        }

        String result = searchResult(flowNumber);

        // 如果交易是"无效的"，根据业务需求可以调用重置"无效"交易的接口，再查询
//        if (RetryTxState.INVALID.name().equals(result)) {
//            if (!BROPRetryUtils.resetInvalidTxWithRetry(flowNumber)) {
//                LOGGER.error("新增黑名单用户失败,调用中间件失败");
//            } else {
//                searchResult(flowNumber);
//            }
//        }

        // 如果交易是"交易处理中的"，根据业务需求可以调用重置"交易处理中的"交易的接口，再查询
//        if (RetryTxState.DOTX.name().equals(result)) {
//            if (!BROPRetryUtils.resetUnfinishedTxWithRetry(flowNumber)) {
//                LOGGER.error("新增黑名单用户失败,调用中间件失败");
//            } else {
//                searchResult(flowNumber);
//            }
//        }
    }

    static String searchResult(String flowNumber) throws InterruptedException {
        // 等待交易上链
        sleep(10 * 1000);

        RetryTxResult result = BROPRetryUtils.queryTxWithRetryResult(flowNumber);

        if (result == null) {
            LOGGER.error("调用中间件失败");
            return null;
        }

        if (RetryTxState.SUCCESS.name().equals(result.getResult())) {
            LOGGER.info("成功");
        } else {
            LOGGER.error("失败");
        }

        return result.getResult();
    }
}
