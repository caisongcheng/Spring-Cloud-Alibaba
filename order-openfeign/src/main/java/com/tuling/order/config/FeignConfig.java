package com.tuling.order.config;

import com.tuling.order.intercptor.feign.CustomFeignInterceptor;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当使用@Configuration注解，会将配置作用在所有的服务提供方（全局配置）
 * 如果不想作用在所有的服务提供方，则不需要加上这个注解
 */
//@Configuration
public class FeignConfig {

    @Bean
    public CustomFeignInterceptor customFeignInterceptor() {
        return new CustomFeignInterceptor();
    }


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // 全局配置超时时间
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
}
