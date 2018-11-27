package com.zchz.brop.apiDemo.req;

import java.io.Serializable;

public class NTSCReq implements Serializable {
    String contractAddress;
    String bizId;
    String blockLimit;
    String method;
    Object[] params;

    public NTSCReq(String contractAddress, String bizId, String blockLimit, String method, Object[] params) {
        this.contractAddress = contractAddress;
        this.bizId = bizId;
        this.blockLimit = blockLimit;
        this.method = method;
        this.params = params;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBlockLimit() {
        return blockLimit;
    }

    public void setBlockLimit(String blockLimit) {
        this.blockLimit = blockLimit;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "{" +
                "contractAddress='" + contractAddress + '\'' +
                ", bizId='" + bizId + '\'' +
                ", blockLimit='" + blockLimit + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
