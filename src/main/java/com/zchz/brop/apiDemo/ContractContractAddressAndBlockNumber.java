package com.zchz.brop.apiDemo;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用合约地址和块号获取合约，同时返回合约业务数据如果不指定块号，则使用最新的块号如果查不到交易，则返回空数组。
 */
public class ContractContractAddressAndBlockNumber {
    private static final Logger logger = LoggerFactory.getLogger(ContractContractAddressAndBlockNumber.class);

    private static String contractAddress = ApplicationConfig.recordRegistryAddress;
    private static Integer blockNumber = BROPUtils.blockNumber() - 1000;

    public static void main(String[] args) {
        CommonResult contractMsg = BROPUtils.getContract(contractAddress, blockNumber);
        logger.info("contractMsg: {}", contractMsg);
    }
}
