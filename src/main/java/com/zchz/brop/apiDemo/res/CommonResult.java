package com.zchz.brop.apiDemo.res;

import com.zchz.brop.apiDemo.enums.BROPResponseCode;

import java.io.Serializable;

public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -2348170602033403775L;
    /**
     * 信息码
     */
    private String code = BROPResponseCode.SUCCESS.name();

    /**
     * 返回数据 |信息码描述信息
     */
    private T detail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", detail=" + detail +
                '}';
    }
}
