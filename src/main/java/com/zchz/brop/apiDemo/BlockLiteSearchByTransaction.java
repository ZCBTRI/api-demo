package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询交易，不包含合约业务数据。
 */
public class BlockLiteSearchByTransaction {
    private static final Logger logger = LoggerFactory.getLogger(BlockLiteSearchByTransaction.class);

    private static String txHash,
            sender,
            bizId,
            blockTimeFrom,
            blockTimeTo,
            format = "yyyy-MM-dd'T'HH:mm:ssXXX";

    static {
        txHash = "9636a6dc04eeeb3b7a44b1264fe9031f7ee3b986aeeea32165e079816a5580ed";
        sender = "5e86350642356a0581608697ee876c5b38eebefb";
        bizId = "1524639841204";
    }

    public static void main(String[] args) {
        // 数据准备
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date blockTimeFromData = new Date();
        blockTimeFromData.setTime(System.currentTimeMillis() - 1000 * 1000000);
        blockTimeFrom = simpleDateFormat.format(blockTimeFromData);
        Date blockTimeToData = new Date();
        blockTimeToData.setTime(System.currentTimeMillis());
        blockTimeTo = simpleDateFormat.format(blockTimeToData);
        CommonResult txMessage = BROPUtils.txMsg(txHash, sender, bizId, blockTimeFrom, blockTimeTo);
        logger.info("txMessage: {}", txMessage);
    }
}