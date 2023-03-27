package com.ey.eshop.common.controller;

import com.ey.eshop.common.constant.RequestAttribute;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.model.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public abstract class BaseController {

    public long getCurrentUserId() {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            RequestContext requestContext = (RequestContext) request.getAttribute(RequestAttribute.REQUEST_CONTEXT);
            return requestContext.getUserId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("请先登录");
        }
    }
}
