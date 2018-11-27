package com.zchz.brop.apiDemo.req;

public class TxMsgReq {
    private String txHash;
    private String sender;
    private String bizId;
    private String blockTimeFrom;
    private String blockTimeTo;
    private int pageSize;
    private int pageNum;

    public TxMsgReq(String txHash, String sender, String bizId, String blockTimeFrom, String blockTimeTo, int pageSize, int pageNum) {
        this.txHash = txHash;
        this.sender = sender;
        this.bizId = bizId;
        this.blockTimeFrom = blockTimeFrom;
        this.blockTimeTo = blockTimeTo;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBlockTimeFrom() {
        return blockTimeFrom;
    }

    public void setBlockTimeFrom(String blockTimeFrom) {
        this.blockTimeFrom = blockTimeFrom;
    }

    public String getBlockTimeTo() {
        return blockTimeTo;
    }

    public void setBlockTimeTo(String blockTimeTo) {
        this.blockTimeTo = blockTimeTo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "{" +
                "txHash='" + txHash + '\'' +
                ", sender='" + sender + '\'' +
                ", bizId='" + bizId + '\'' +
                ", blockTimeFrom='" + blockTimeFrom + '\'' +
                ", blockTimeTo='" + blockTimeTo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNum='" + pageNum + '\'' +
                '}';
    }
}
