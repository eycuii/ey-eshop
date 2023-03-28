package com.ey.eshop.common.config;

import com.ey.eshop.common.constant.RequestAttribute;
import com.ey.eshop.common.model.RequestContext;
import com.ey.eshop.common.util.JwtUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestContext requestContext = (RequestContext) request.getAttribute(RequestAttribute.REQUEST_CONTEXT);
        String token;
        if (requestContext != null) {
            token = requestContext.getToken();
        } else {
            token = request.getHeader(JwtUtil.JWT_HEADER_KEY);
        }
        requestTemplate.header(JwtUtil.JWT_HEADER_KEY, token);
    }
}
