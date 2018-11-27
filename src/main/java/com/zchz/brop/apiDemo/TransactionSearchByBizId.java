package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用bizId查询交易，获得该bizId关联的交易数据数组，同时返回该交易相关联的合约数据快照。如果查不到交易，则返回空数组。
 */
public class TransactionSearchByBizId {
    private static final Logger logger = LoggerFactory.getLogger(TransactionSearchByBizId.class);

    private static String bizId = "2865757419974262";

    public static void main(String[] args) {
        CommonResult txMsg = BROPUtils.getTxMessageByBizId(bizId);
        logger.info("txMessage: {}", txMsg.getDetail());
    }
}
