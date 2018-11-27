package com.zchz.brop.apiDemo.res;

import java.util.Map;

public class ContractResult {

    private String contractAddress;
    private String contractName;
    private String operateType;
    private Map<String, Object> contractData;

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Map<String, Object> getContractData() {
        return contractData;
    }

    public void setContractData(Map<String, Object> contractData) {
        this.contractData = contractData;
    }

    @Override
    public String toString() {
        return "ContractResult{" +
                "contractAddress='" + contractAddress + '\'' +
                ", contractName='" + contractName + '\'' +
                ", operateType='" + operateType + '\'' +
                ", contractData=" + contractData +
                '}';
    }
}
