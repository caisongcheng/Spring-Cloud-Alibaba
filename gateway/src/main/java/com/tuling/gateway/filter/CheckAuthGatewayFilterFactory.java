package com.tuling.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
// 名字必须要GatawayFilterFactory结尾，因为内部实现是根据名字反射去获取的
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {


    public CheckAuthGatewayFilterFactory() {
        super(CheckAuthGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            // 当请求的参数不带name时，请求成功
            // 当请求的参数带name，则值为xushu成功
            // 值不为xushu,返回404
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                // 获取参数name的值
                // 比较获取到的值是不是等于xushu
                // 只是为了演示自定义过滤器，其实实现的这个功能应该是路由断言工厂干的活
                String name = exchange.getRequest().getQueryParams().getFirst("name");
                if (StringUtils.isNotBlank(name)) {
                    if (config.getValue().equals(name)) {
                        return chain.filter(exchange);
                    } else {
                        // 设置错误码为404
                        exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                        return exchange.getResponse().setComplete();
                    }
                }
                return chain.filter(exchange);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("value");
    }

    @Validated
    public static class Config {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
