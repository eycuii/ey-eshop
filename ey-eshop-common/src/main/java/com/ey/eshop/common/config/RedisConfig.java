package com.ey.eshop.common.config;

import com.ey.eshop.common.cache.RedisCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    /**
     * 插件配置
     */
    @Bean
    public RedisCache redisCache(StringRedisTemplate redisTemplate) {
        return new RedisCache(redisTemplate);
    }
}