package com.ey.eshop.common.enums;

public enum CommonResultCodeEnum {

    SUCCESS("00000", "success"),
    SYSTEM_ERROR("00001", "系统异常"),
    SESSION_ERROR("00002", "会话异常"),
    BUSINESS_ERROR("00003", "请求异常"),
    CLIENT_ERROR("00004", "请求错误");

    private final String code;
    private final String message;

    CommonResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}