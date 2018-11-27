package com.zchz.brop.apiDemo.enums;

/**
 * @author wangmin@zccp.com
 * @version 2018/10/26 4:52 PM
 */
public enum RetryTxState {

    INIT("初始化"),
    ONCHAIN("上链中"),
    DOTX("交易处理中"),
    RETRY("等待重试"),
    SUCCESS("成功"),
    INVALID("无效");

    private String desc;

    RetryTxState(String desc) {
        this.desc = desc;
    }
}
