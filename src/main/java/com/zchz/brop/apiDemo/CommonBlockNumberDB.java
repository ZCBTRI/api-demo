package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取当前同步块号
 */
public class CommonBlockNumberDB {
    private static final Logger logger = LoggerFactory.getLogger(CommonBlockNumberDB.class);

    public static void main(String[] args) {
        Integer blockNumberDB = BROPUtils.blockNumberDB();
        logger.info("blockNumberDB: {}", blockNumberDB);
    }
}
