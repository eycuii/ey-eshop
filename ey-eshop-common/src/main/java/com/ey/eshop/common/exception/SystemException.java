package com.ey.eshop.common.exception;

import com.ey.eshop.common.enums.CommonResultCodeEnum;

public class SystemException extends BaseException {

    public SystemException() {
        super(CommonResultCodeEnum.SYSTEM_ERROR);
    }

    public SystemException(String message) {
        super(CommonResultCodeEnum.SYSTEM_ERROR, message);
    }
}
