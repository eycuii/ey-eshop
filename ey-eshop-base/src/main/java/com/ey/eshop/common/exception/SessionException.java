package com.ey.eshop.common.exception;

import com.ey.eshop.common.enums.CommonResultCodeEnum;

public class SessionException extends BaseException {

    public SessionException(String message) {
        super(CommonResultCodeEnum.SESSION_ERROR, message);
    }
}
