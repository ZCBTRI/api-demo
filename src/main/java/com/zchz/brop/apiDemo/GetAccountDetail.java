package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用身份合约的合约地址，获取该身份合约的身份信息
 */
public class GetAccountDetail {
    private static final Logger logger = LoggerFactory.getLogger(GetAccountDetail.class);
    // 当前身份合约的合约地址
    private static final String contractAddress = ApplicationConfig.coopAddress;
    // 方法名
    private static final String functionName = "getAccountDetail";

    public static void main(String[] args) {
        CommonResult commonResult = BROPUtils.contract(contractAddress, functionName, new Object[]{});
        logger.info("该用户身份合约的身份信息为：\n {}", commonResult.getDetail());
    }
}
