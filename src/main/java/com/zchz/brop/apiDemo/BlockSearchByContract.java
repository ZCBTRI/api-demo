package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询合约，包含合约业务数据
 */
public class BlockSearchByContract {
    private static final Logger logger = LoggerFactory.getLogger(BlockSearchByContract.class);

    private static String contract,
            blockTimeFrom,
            blockTimeTo;
    private static int blockNumber;

    static {
        contract = ""; // 存证合约记录地址
        blockNumber = BROPUtils.blockNumber() - 200;
    }
    public static void main(String[] args) {
        // 数据准备
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date blockTimeFromData = new Date();
        blockTimeFromData.setTime(System.currentTimeMillis() - 1000000 * 1000);
        blockTimeFrom = simpleDateFormat.format(blockTimeFromData);
        Date blockTimeToData = new Date();
        blockTimeToData.setTime(System.currentTimeMillis());
        blockTimeTo = simpleDateFormat.format(blockTimeToData);
        CommonResult contractMessage = BROPUtils.searchContarct(contract, blockNumber, blockTimeFrom, blockTimeTo);
        logger.info("contarctMsg: {}", contractMessage);
    }
}
