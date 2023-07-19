package com.tuling.order.controller;

import com.tuling.order.feign.ProductFeignService;
import com.tuling.order.feign.StockFeignService;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private ProductFeignService productFeignService;

    // restTemplate方式调用
//    @RequestMapping("/add")
//    public String add() {
//        System.out.println("下单成功");
//        String msg = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
//        return "Hello World" + msg;
//    }

    // openFeign方式调用
    @RequestMapping("/add")
    public String add() {
        // restTemplate可以设置超时时间，详见order-nacos
        System.out.println("下单成功");
        String msg = stockFeignService.reduct();
        return "Hello OpenFeign" + msg;
    }

    @RequestMapping("/addAll")
    public String addAll() {
        System.out.println("下单成功");
        String msg = stockFeignService.reduct();
        String pro = productFeignService.get(1);
        return "Hello Feign " + msg + pro;
    }

}
