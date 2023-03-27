package com.ey.eshop.common.model;

import com.ey.eshop.common.enums.CommonResultCodeEnum;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private T data;
    private String msg;

    private Result() {
    }

    public static <T> Result<T> buildSuccess(T data) {
        Result<T> result = new Result<>();
        result.code = CommonResultCodeEnum.SUCCESS.getCode();
        result.data = data;
        return result;
    }

    public static <T> Result<T> buildError(String code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
