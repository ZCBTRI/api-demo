package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 检查用户是否具有往指定存证合约内写入存证记录的权限。。
 */
public class HasWriteRecordPermission {
    private static final Logger logger = LoggerFactory.getLogger(HasWriteRecordPermission.class);
    // 存证代理合约地址
    private static final String contractAddress = ApplicationConfig.registryProxyAddress;
    // 方法名
    private static final String function = "hasWriteRecordPermission";
    // 存证合约地址
    private static final String registryAddress = "3253f34115968b7c45c343dad2d7f3f521be426c";
    // 账户 ID
    private static final Integer accountId = 100000007;

    public static void main(String[] args) {
        CommonResult<Map<String, Object>> commonResult = BROPUtils.contract(contractAddress, function, new Object[]{ registryAddress, accountId });
        logger.info("是否有写入存证记录权限: {}", commonResult.getDetail().get("hasPermission"));
    }
}
