package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 使用身份合约的账户ID(如100000002)获取对应身份合约的合约地址
 */
public class GetAccountAddressByAccountId {
    private static final Logger logger = LoggerFactory.getLogger(GetAccountAddressByAccountId.class);
    // 身份代理合约的合约地址
    private static final String accountProxyAddress = ApplicationConfig.accountProxyAddress;
    // 方法名
    private static final String functionName = "getAccountAddressByAccountId";
    // 账户ID
    private static final Integer params = Integer.parseInt(ApplicationConfig.coopAccountId);

    public static void main(String[] args) {
        CommonResult<Map<String, Object>> commonResult = BROPUtils.contract(accountProxyAddress, functionName,
                new Object[] { params });
        logger.info("该用户身份合约的合约地址为：{}", commonResult.getDetail().get("accountAddress"));
    }
}
