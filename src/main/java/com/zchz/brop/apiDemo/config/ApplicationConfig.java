package com.zchz.brop.apiDemo.config;

public class ApplicationConfig {
    public static final String
            middleware,
            hsmServer,
            recordRegistryAddress, // 存证合约地址
            registryNTSCAddress, // 授时合约地址
            accountProxyAddress, // 身份代理合约地址
            registryProxyAddress, // 存证代理合约地址
            registryNTSCProxy,    // 授时代理合约
            hsmName, // 加密机签名userId
            coopAddress, // 合作方身份地址
            coopAccountId, // 合作方Id
            rocketAddress; // 消息队列地址


    public static final Integer pageSize, pageNum;

    static {
        middleware = "http://192.168.2.33:9999";
        hsmServer = "http://192.168.2.33:8080";
        accountProxyAddress = "7888e60bd1c79f2f81eb2a34e4d63f7f19df71e2";
        registryProxyAddress = "cf864da68b1f3621763139fc9d401a4e1039df64";
        recordRegistryAddress = "dfe021dbbfc2d01aba27a45aacf0fb7b023f5eb3";
        registryNTSCProxy = "bf56241610856cfacf5b2c527bb231ba74c13c3e";
        registryNTSCAddress = "87fb531bdaf9c10a2b2da08196011f6b0779b7c8";
        hsmName = "zccpdy";
        coopAddress = "654e2918a31420e747a121180ff0437f074cc5d8";
        coopAccountId = "100000051";
        pageNum = 1;
        pageSize = 10;
        rocketAddress = "192.168.93.132:9876";
    }
}
