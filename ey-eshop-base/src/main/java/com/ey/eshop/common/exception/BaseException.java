package com.ey.eshop.common.exception;

import com.ey.eshop.common.enums.CommonResultCodeEnum;
import com.ey.eshop.common.util.StringUtil;

public abstract class BaseException extends RuntimeException {

    private final String code;

    public BaseException(CommonResultCodeEnum baseErrorCodeEnum) {
        super(baseErrorCodeEnum.getMessage());
        this.code = CommonResultCodeEnum.SYSTEM_ERROR.getCode();
    }

    public BaseException(CommonResultCodeEnum baseErrorCodeEnum, String message) {
        super(StringUtil.isEmpty(message) ? baseErrorCodeEnum.getMessage() : message);
        this.code = baseErrorCodeEnum.getCode();
    }

    public String getCode() {
        return code;
    }
}
