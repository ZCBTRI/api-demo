package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用txHash查询交易，返回单条交易数据，同时返回该交易相关联的合约业务数据快照。
 */
public class TransactionTxHash {
    private static final Logger logger = LoggerFactory.getLogger(TransactionTxHash.class);

    private static String txHash = "eecb7bb73bfa939b51e400614c8225fbd44f782ad6a41564725ce6116b6a3efa";
    public static void main(String[] args) {
        CommonResult txMessage = BROPUtils.getTxMessage(txHash);
        logger.info("txMessage: {}", txMessage);
    }
}
