package com.zchz.brop.apiDemo.res;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class TransactionResult {
    private String txHash;
    private String bizId;
    private String sender;
    private Integer resultCode;
    private BigInteger blockNumber;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "GMT+8")
    private Date blockTime;
    private List<ContractResult> dataSnapshots;

    public String getTxHash() {
        return txHash;
    }

    @Override
    public String toString() {
        return "TransactionResult{" +
                "txHash='" + txHash + '\'' +
                ", bizId='" + bizId + '\'' +
                ", sender='" + sender + '\'' +
                ", resultCode=" + resultCode +
                ", blockNumber=" + blockNumber +
                ", blockTime=" + blockTime +
                ", dataSnapshots=" + dataSnapshots +
                '}';
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Date getBlockTime() {
        return blockTime == null ? null : new Date(blockTime.getTime());
    }

    public void setBlockTime(Date blockTime) {
        this.blockTime = blockTime == null ? null : new Date(blockTime.getTime());
    }

    public List<ContractResult> getDataSnapshots() {
        return dataSnapshots;
    }

    public void setDataSnapshots(List<ContractResult> dataSnapshots) {
        this.dataSnapshots = dataSnapshots;
    }

}
