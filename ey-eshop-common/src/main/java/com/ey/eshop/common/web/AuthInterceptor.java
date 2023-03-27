package com.ey.eshop.common.web;

import com.ey.eshop.common.constant.RequestAttribute;
import com.ey.eshop.common.model.RequestContext;
import com.ey.eshop.common.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(JwtUtil.JWT_HEADER_KEY);
        Long userId = JwtUtil.validateToken(token);
        RequestContext requestContext = RequestContext.builder()
                .token(token)
                .userId(userId)
                .build();
        request.setAttribute(RequestAttribute.REQUEST_CONTEXT, requestContext);
        return true;
    }
}
