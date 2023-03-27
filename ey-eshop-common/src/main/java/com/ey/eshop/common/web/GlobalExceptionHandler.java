package com.ey.eshop.common.web;

import com.ey.eshop.common.enums.CommonResultCodeEnum;
import com.ey.eshop.common.exception.BaseException;
import com.ey.eshop.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public Result<Object> exceptionHandler(BaseException e) {
        log.error(e.getMessage(), e);
        return Result.buildError(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    public Result<Object> exceptionHandler(Throwable e) {
        log.error(e.getMessage(), e);
        return Result.buildError(
                CommonResultCodeEnum.SYSTEM_ERROR.getCode(), CommonResultCodeEnum.SYSTEM_ERROR.getMessage());
    }

    // =========== 客户端异常 =========

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result<Object> handle(HttpRequestMethodNotSupportedException e) {
        log.error("客户端HTTP请求方法错误: {}", e.getMessage(), e);
        return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), "客户端HTTP请求方法错误");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Object> handle(MethodArgumentNotValidException e) {
        log.error("客户端请求体参数校验不通过: {}", e.getMessage(), e);
        String errorMsg = this.handle(e.getBindingResult().getFieldErrors());
        return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), errorMsg);
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            sb.append(obj.getField());
            sb.append("=[");
            sb.append(obj.getDefaultMessage());
            sb.append("]  ");
        }
        return sb.toString();
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result<Object> handle(HttpMessageNotReadableException e) {
        log.error("客户端请求体JSON格式错误或字段类型不匹配: {}", e.getMessage(), e);
        return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), "客户端请求体JSON格式错误或字段类型不匹配");
    }

    @ExceptionHandler(value = BindException.class)
    public Result<Object> handle(BindException e) {
        log.error("客户端URL中的参数类型错误: {}", e.getMessage(), e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMsg = null;
        if (fieldError != null) {
            errorMsg = fieldError.getDefaultMessage();
            if (errorMsg != null && errorMsg.contains("java.lang.NumberFormatException")) {
                errorMsg = fieldError.getField() + "参数类型错误";
            }
        }
        if (errorMsg != null && !"".equals(errorMsg)) {
            return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), errorMsg);
        }
        return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), "客户端URL中的参数类型错误");
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<Object> handle(MissingServletRequestParameterException e) {
        log.error("客户端请求缺少必填的参数: {}", e.getMessage(), e);
        String errorMsg = null;
        String parameterName = e.getParameterName();
        if (!"".equals(parameterName)) {
            errorMsg = parameterName + "不能为空";
        }
        if (errorMsg != null) {
            return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), errorMsg);
        }
        return Result.buildError(CommonResultCodeEnum.CLIENT_ERROR.getCode(), "客户端请求缺少必填的参数");
    }
}
