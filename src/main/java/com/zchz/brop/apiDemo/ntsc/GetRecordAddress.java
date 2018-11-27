package com.zchz.brop.apiDemo.ntsc;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 授时存证合约
 * 根据存证流水号获取存证记录合约的合约地址。
 */
public class GetRecordAddress {
    private static final Logger logger = LoggerFactory.getLogger(GetRecordAddress.class);
    // 授时存证合约的合约地址
    private static final String dataUnitContractAddress = ApplicationConfig.registryNTSCAddress;
    // 方法名
    private static final String functionName = "getRecordAddress";
    // 存证流水号，必填，每次写入都需要更新，存证流水号必须唯一，重复提交会失败
    public static final String seqId = "test1526552394144";

    public static void main(String[] args) {
        CommonResult<Map<String, Object>> commonResult = BROPUtils.contract(dataUnitContractAddress, functionName,
                new Object[] { seqId });
        logger.info("该存证记录合约的合约地址为：{}", commonResult.getDetail().get("record"));
    }
}
