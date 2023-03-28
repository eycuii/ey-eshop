package com.ey.eshop.common.config;

import com.ey.eshop.common.web.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({GlobalExceptionHandler.class, SwaggerConfig.class,
        MybatisPlusConfig.class, RedisConfig.class,
        FeignConfig.class})
public class CommonAutoConfig {
}
