package com.zchz.brop.apiDemo.enums;

public enum BROPContractResultcode {
    CODE_SUCCESS("成功", 0),
    CODE_NO_CHAIN_TX_PERMISSION("没有链发送交易权限", 101),
    CODE_NO_CHAIN_CONTRACT_PERMISSION("没有链部署合约的权限", 102),
    CODE_NO_CHAIN_ADMIN_PERMISSION("没有链管理员权限", 103),
    CODE_NO_REGISTRY_PERMISSION("没有创建存证合约的权限", 104),
    CODE_NO_ACCOUNT_PERMISSION("没有创建身份合约的权限", 105),
    CODE_NO_RECORD_PERMISSION("没有写入存证记录的权限", 106),
    CODE_NOT_ACCOUNT_BUILDER("不是当前身份的创建者", 107),
    CODE_NOT_OPERATOR("operator的身份合约没有包含发送交易的外部账户地址", 108),
    CODE_NOT_ACCOUNT_CONTRACT("不是身份合约地址", 109),
    CODE_NOT_RECORD_REGISTRY_CONTRACT("不是存证合约地址", 110),
    CODE_MSG_SENDER_NOT_ALLOWED("不允许的 msg.sender", 111),
    CODE_NOT_RECORD_REGISTRY_NTSC_CONTRACT("不是授时存证合约", 112),
    CODE_REGISTRY_BO_NOT_IN_WHITE_LIST("不在白名单", 501),
    CODE_REGISTRY_BO_IN_BLACK_LIST("在黑名单", 502),
    CODE_REGISTRY_BO_NOT_ADMIN("不是当前存证合约的管理员", 503),
    CODE_VAL_ACCOUNT_BO_ACCOUNT_NAME("数据校验，账户名，字节长度范围 [1, 255]", 601),
    CODE_VAL_ACCOUNT_BO_AUTH_TYPE("数据校验，认证级别，字节长度大于 0", 602),
    CODE_VAL_ACCOUNT_BO_ACCOUNT_PERMISSION("数据校验，创建身份智能合约权限，具有继承特性", 603),
    CODE_VAL_ACCOUNT_BO_RECORD_PERMISSION("数据校验，写入存证记录的权限，具有继承特性", 604),
    CODE_VAL_ACCOUNT_BO_CREDENTIAL_PERMISSION("数据校验，管理数字凭证智能合约权限，具有继承特性", 605),
    CODE_VAL_ACCOUNT_BO_CONTRACT_DIGEST("数据校验，签约文件摘要，字节长度大于 0", 606),
    CODE_VAL_ACCOUNT_BO_CERTIFICATE("数据校验，CA证书相关信息，如果 authType 为 CA，则字节长度大于 0", 607),
    CODE_VAL_ACCOUNT_BO_ACCOUNT_INFO("数据校验，身份公示信息，字节长度小于等于 400", 608),
    CODE_VAL_REGISTRY_NAME("数据校验，名称，字节长度范围 [1, 200]", 701),
    CODE_VAL_REGISTRY_DESC("数据校验，描述，字节长度小于等于 1000", 702),
    CODE_VAL_REGISTRY_MGMT_ACCOUNT_ID("数据校验，管理员账户ID，检查是否存在", 703),
    CODE_VAL_REGISTRY_CONTRACT_DIGEST("数据校验，签约文件摘要，字节长度范围 [1, 200]", 704),
    CODE_VAL_REGISTRY_ACCOUNT_ID("数据校验， accountId 是否有对应的身份地址存在", 705),
    CODE_VAL_RECORD_SEQ_ID("数据校验，存证流水号，该流水号不能被占用，字节长度范围 [1, 48]", 801),
    CODE_VAL_RECORD_PRE_SEQ_ID("数据校验，前序存证流水号，如果为非空字符串，则必须已经存在相应的存证记录，字节长度小于等于 48", 802),
    CODE_VAL_RECORD_RECORD_DATA("数据校验，存证数据，字节长度范围 [1, 128]", 803),
    CODE_VAL_RECORD_CUSTOM_STRING("数据校验，自定义字段，字节长度小于等于 800", 804),
    CODE_VAL_RECORD_NTSC_TIME("授时时间，字节长度小于等于 30", 805),
    CODE_VAL_RECORD_NTSC_SIGNATURE("授时签名，字节长度小于等于 2000", 806),
    CODE_EXT_ERROR_AUTHORIZATION("非管理员用户无法执行该操作", 901),
    CODE_EXT_ERROR_VOID_CONTRACT("扩展挂载失败,目标合约无效", 902),
    CODE_EXT_ERROR_UNKNOWN("扩展挂载失败。传入的合约数量和接口数量不匹配", 903);


    private String message;
    private int status;

    BROPContractResultcode(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public static String getCodeMessage(int state) {
        BROPContractResultcode[] bropContractResultcodes = BROPContractResultcode.values();
        for (BROPContractResultcode s: bropContractResultcodes) {
            if (s.getStatus() == state) {
                return s.getMessage();
            }
        }
        return null;
    }
}
