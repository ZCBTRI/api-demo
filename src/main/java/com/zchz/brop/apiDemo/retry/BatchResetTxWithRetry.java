package com.zchz.brop.apiDemo.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangmin@zccp.com
 * @version 2018/10/29 4:23 PM
 */
public class BatchResetTxWithRetry {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchResetTxWithRetry.class);

    public static void main(String[] args) throws InterruptedException {
        // 状态为"无效"的交易流水号
        String[] invalidFlowNumbers = new String[] {
                "1509779266266751170",
                "1509779266266751171",
                "1509779266266751172"
        };

        // 调用重置全部"无效"交易的接口，再查询
        if (!BROPRetryUtils.resetInvalidTxWithRetry(null)) {
            LOGGER.error("调用中间件失败");
        } else {
            for (String invalidFlowNumber : invalidFlowNumbers) {
                AddUserToBlackListWithRetry.searchResult(invalidFlowNumber);
            }
        }

        // 状态为"交易处理中"的交易流水号
        String[] unfinishedFlowNumbers = new String[] {
                "1509779266266751170",
                "1509779266266751171",
                "1509779266266751172"
        };

        // 调用重置全部"交易处理中"交易的接口，再查询
        if (!BROPRetryUtils.resetUnfinishedTxWithRetry(null)) {
            LOGGER.error("调用中间件失败");
        } else {
            for (String unfinishedFlowNumber : unfinishedFlowNumbers) {
                AddUserToBlackListWithRetry.searchResult(unfinishedFlowNumber);
            }
        }

    }

}
