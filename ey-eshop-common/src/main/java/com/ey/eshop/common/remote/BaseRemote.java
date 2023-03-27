package com.ey.eshop.common.remote;

import com.ey.eshop.common.enums.CommonResultCodeEnum;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.model.Result;

public abstract class BaseRemote {

    protected <T> Result<T> handleResult(Result<T> result) {
        if (CommonResultCodeEnum.SUCCESS.getCode().equals(result.getCode())) {
            return result;
        }
        throw new BusinessException(result.getMsg());
    }
}
