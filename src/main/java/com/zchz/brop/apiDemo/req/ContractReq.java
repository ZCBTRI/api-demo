package com.zchz.brop.apiDemo.req;

import java.util.Arrays;

public class ContractReq {

    private String contractAddress;

    private String functionName;

    private Object[] params;

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
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
                ", functionName='" + functionName + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
