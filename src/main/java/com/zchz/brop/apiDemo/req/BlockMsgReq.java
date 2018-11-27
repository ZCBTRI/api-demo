package com.zchz.brop.apiDemo.req;

public class BlockMsgReq {
    // 块号大于等于该值
    private String blockNumberFrom;
    // 块号小于等于该值
    private String blockNumberTo;
    // 块时间大于等于该时间值
    private String blockTimeFrom;
    // 块时间小于该时间值
    private String blockTimeTo;
    // 每页记录数
    private int pageSize;
    // 查询的指定页
    private int pageNum;

    public BlockMsgReq(String blockNumberFrom, String blockNumberTo, String blockTimeFrom, String blockTimeTo, int pageSize, int pageNum) {
        this.blockNumberFrom = blockNumberFrom;
        this.blockNumberTo = blockNumberTo;
        this.blockTimeFrom = blockTimeFrom;
        this.blockTimeTo = blockTimeTo;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public String getBlockNumberFrom() {
        return blockNumberFrom;
    }

    public void setBlockNumberFrom(String blockNumberFrom) {
        this.blockNumberFrom = blockNumberFrom;
    }

    public String getBlockNumberTo() {
        return blockNumberTo;
    }

    public void setBlockNumberTo(String blockNumberTo) {
        this.blockNumberTo = blockNumberTo;
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
                "blockNumberFrom='" + blockNumberFrom + '\'' +
                ", blockNumberTo='" + blockNumberTo + '\'' +
                ", blockTimeFrom='" + blockTimeFrom + '\'' +
                ", blockTimeTo='" + blockTimeTo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNum='" + pageNum + '\'' +
                '}';
    }
}
