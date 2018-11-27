package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取当前块号
 */
public class CommonBlockNumber {
    private static final Logger logger = LoggerFactory.getLogger(CommonBlockNumber.class);

    public static void main(String[] args) {
        Integer blockNumber = BROPUtils.blockNumber();
        logger.info("blockNumber: {}", blockNumber);
    }
}
