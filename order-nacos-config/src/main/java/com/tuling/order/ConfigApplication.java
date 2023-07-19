package com.tuling.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(ConfigApplication.class, args);
        while (true) {
            String name = context.getEnvironment().getProperty("user.name");
            String age = context.getEnvironment().getProperty("user.age");
            String config = context.getEnvironment().getProperty("user.config");
            System.out.println("name: " + name + ", age: " + age + ",config: " + config);
            TimeUnit.SECONDS.sleep(1);
        }
    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

}
