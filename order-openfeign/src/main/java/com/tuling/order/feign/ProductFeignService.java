package com.tuling.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service", path = "/product")
public interface ProductFeignService {

    @RequestMapping("/{id}")
    // Feign定义接口时，pathvariable需要指定参数，不像SpringMvc使用时，如果不加代表默认就是指的id
    String get(@PathVariable("id") Integer id);

}
