package com.tuling.order.feign;

import com.tuling.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * name 指定被调用接口所对应的服务名
 * path 指定被调用接口所在的controller指定的RequestMapping
 * configuration指定对应的配置类，（局部配置）
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockFeignService {

    // 声明需要调用的rest接口对应的方法
    @RequestMapping("/reduct")
    String reduct();
}
