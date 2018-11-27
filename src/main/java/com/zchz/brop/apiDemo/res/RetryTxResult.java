package com.zchz.brop.apiDemo.res;

/**
 * @author wangmin@zccp.com
 * @version 2018/10/26 11:58 AM
 */
public class RetryTxResult {

    private Long blockLimit;

    private Object[] params;

    private String bizId;

    private String contractAddress;

    private String method;

    private String hsmUserId;

    private String createTime;

    private String updateTime;

    private String txHash;

    private Integer retryTime;

    private String result;

    private String desc;

    public Long getBlockLimit() {
        return blockLimit;
    }

    public void setBlockLimit(Long blockLimit) {
        this.blockLimit = blockLimit;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHsmUserId() {
        return hsmUserId;
    }

    public void setHsmUserId(String hsmUserId) {
        this.hsmUserId = hsmUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
