package com.tuling.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功");
        String msg = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
        return "Hello World" + msg;
    }

    // 请求路径为：/order/showUser?userName=123&password=aaa
    @RequestMapping("/showUser")
    public String show(@RequestParam String userName, @RequestParam String password) {
        return userName + " " + password;
    }

    // 请求路径为：/order/show/123/aaa
    @RequestMapping("/show/{userName}/{pwd}")
    public String show1(@PathVariable(name = "userName") String name, @PathVariable(name = "pwd") String password) {
        return name + " " + password;
    }

    @RequestMapping("/header")
    public String getHeaderInfo(@RequestHeader("X-Request-color")String color) {
        log.info("gateway获取请求头X-Request-color：" + color);
        return "success";
    }

    @RequestMapping("/testParam")
    public String testParam(@RequestParam String color) {
        log.info("gateway获取请求参数color的值：" + color);
        return color;
    }

    @RequestMapping("/flow")
    //@SentinelResource(value = "flow",blockHandler = "flowBlockHandler")
    public String flow() throws InterruptedException {
        return "正常访问";
    }



    @RequestMapping("/flowThread")
    //@SentinelResource(value = "flowThread",blockHandler = "flowBlockHandler")
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("正常访问");
        return "正常访问";
    }


    // 关联流控  访问/add 触发/get
    @RequestMapping("/get")
    public String get() throws InterruptedException {
        return "查询订单";
    }



    @RequestMapping("/err")
    public String err()  {
        int a=1/0;
        return "hello";
    }


    /**
     * 热点规则，必须使用@SentinelResource
     * @param id
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/get/{id}")
    public String getById(@PathVariable("id") Integer id) throws InterruptedException {

        System.out.println("正常访问");
        return "正常访问";
    }

}
