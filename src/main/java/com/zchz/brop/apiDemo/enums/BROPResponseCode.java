package com.zchz.brop.apiDemo.enums;

public enum BROPResponseCode {

    SUCCESS("运行成功", 200);

    private String message;
    private int status;

    BROPResponseCode(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
}
