package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询块信息，不包含合约业务数据。
 */
public class BlockLiteSearch {
    private static final Logger logger = LoggerFactory.getLogger(BlockLiteSearch.class);
    private static String
            blockNumberFrom,
            blockNumberTo,
            blockTimeFrom,
            blockTimeTo,
            format = "yyyy-MM-dd'T'HH:mm:ssXXX";

    static {
        blockNumberFrom = (BROPUtils.blockNumber()-3000) + "";
        blockNumberTo = BROPUtils.blockNumber() + "";
    }
    public static void main(String[] args) {
        // 数据准备
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date blockTimeFromData = new Date();
        blockTimeFromData.setTime(System.currentTimeMillis() - 10000 * 1000);
        blockTimeFrom = simpleDateFormat.format(blockTimeFromData);
        Date blockTimeToData = new Date();
        blockTimeToData.setTime(System.currentTimeMillis());
        blockTimeTo = simpleDateFormat.format(blockTimeToData);
        CommonResult blockMsg = BROPUtils.blockMsg(blockNumberFrom, blockNumberTo, blockTimeFrom, blockTimeTo);
        logger.info("块信息为: {}", blockMsg.getDetail());
    }
}
