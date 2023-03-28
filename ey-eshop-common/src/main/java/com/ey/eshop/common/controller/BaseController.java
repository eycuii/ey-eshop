package com.ey.eshop.common.controller;

import com.ey.eshop.common.constant.RequestAttribute;
import com.ey.eshop.common.exception.BaseException;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.model.RequestContext;
import com.ey.eshop.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public abstract class BaseController {

    public long getCurrentUserId() {
        try {
            ServletRequestAttributes servletRequestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            RequestContext requestContext = (RequestContext) request.getAttribute(RequestAttribute.REQUEST_CONTEXT);
            if (requestContext != null) {
                return requestContext.getUserId();
            } else {
                String token = request.getHeader(JwtUtil.JWT_HEADER_KEY);
                Long userId = JwtUtil.validateToken(token);
                request.setAttribute(RequestAttribute.REQUEST_CONTEXT,
                        RequestContext.builder()
                                .token(token)
                                .userId(userId)
                                .build());
                return userId;
            }
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("请先登录");
        }
    }
}
