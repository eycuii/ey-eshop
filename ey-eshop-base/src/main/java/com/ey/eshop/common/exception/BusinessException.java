package com.ey.eshop.common.exception;

import com.ey.eshop.common.enums.CommonResultCodeEnum;

public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(CommonResultCodeEnum.BUSINESS_ERROR, message);
    }
}
