package com.zchz.brop.apiDemo.req;

public class SearchByContarctReq {
    private String contract;
    private int blockNumber;
    private String blockTimeFrom;
    private String blockTimeTo;
    private int pageSize;
    private int pageNum;

    public SearchByContarctReq(String contract, int blockNumber, String blockTimeFrom, String blockTimeTo, int pageSize, int pageNum) {
        this.contract = contract;
        this.blockNumber = blockNumber;
        this.blockTimeFrom = blockTimeFrom;
        this.blockTimeTo = blockTimeTo;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
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
                "contract='" + contract + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", blockTimeFrom='" + blockTimeFrom + '\'' +
                ", blockTimeTo='" + blockTimeTo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNum='" + pageNum + '\'' +
                '}';
    }
}
