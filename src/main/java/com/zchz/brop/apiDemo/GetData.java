package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 获取存证记录合约的信息，一次可以取到一个字段的内容。
 */
public class GetData {
    private static final Logger logger = LoggerFactory.getLogger(GetData.class);
    // 存证记录合约的合约地址
    private static final String contractAddress = "11188583b0edaf9bef179c8d47463a33f09b98a5";
    // 方法名
    private static final String function = "getData";

    public static void main(String[] args) {
        CommonResult<Map<String, Object>> commonResult = BROPUtils.contract(contractAddress, "getData", new Object[]{});
        logger.info("数据读取结果: \n 存证流水号: {}, \n 前序存证流水号: {}, \n 存证信息: {}, \n 自定义字符串: {}",
                commonResult.getDetail().get("seqId"),
                commonResult.getDetail().get("preSeqId"),
                commonResult.getDetail().get("recordData"),
                commonResult.getDetail().get("customString"));
    }
}
