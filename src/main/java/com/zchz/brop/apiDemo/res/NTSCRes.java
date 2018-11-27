package com.zchz.brop.apiDemo.res;

public class NTSCRes {
    private String rawText;
    private Long ntscTime;
    private String ntscSignature;

    public NTSCRes(String rawText, Long ntscTime, String ntscSignature) {
        this.rawText = rawText;
        this.ntscTime = ntscTime;
        this.ntscSignature = ntscSignature;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public Long getNtscTime() {
        return ntscTime;
    }

    public void setNtscTime(Long ntscTime) {
        this.ntscTime = ntscTime;
    }

    public String getNtscSignature() {
        return ntscSignature;
    }

    public void setNtscSignature(String ntscSignature) {
        this.ntscSignature = ntscSignature;
    }

    @Override
    public String toString() {
        return "{" +
                "rawText='" + rawText + '\'' +
                ", ntscTime=" + ntscTime +
                ", ntscSignature='" + ntscSignature + '\'' +
                '}';
    }
}
