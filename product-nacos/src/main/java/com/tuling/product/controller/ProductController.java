package com.tuling.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/{id}")
    public String get(@PathVariable Integer id) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("查询商品 " + id);
        return "查询商品 " + id + port;


    }

}
