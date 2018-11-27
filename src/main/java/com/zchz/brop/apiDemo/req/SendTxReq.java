package com.zchz.brop.apiDemo.req;

import java.util.Arrays;

public class SendTxReq {

    private String contractAddress;

    private String bizId;

    private String blockLimit;

    private String method;

    private Object[] params;

    private String signatureInBase64;

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

    public String getSignatureInBase64() {
        return signatureInBase64;
    }

    public void setSignatureInBase64(String signatureInBase64) {
        this.signatureInBase64 = signatureInBase64;
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
                ", params=" + Arrays.toString(params) +
                ", signatureInBase64='" + signatureInBase64 + '\'' +
                '}';
    }
}
