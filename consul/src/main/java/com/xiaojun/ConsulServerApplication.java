package com.xiaojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojun
 * @date 2018/9/2 21:48
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulServerApplication {

    @RequestMapping("/health")
    public String health() {
        return "consul server";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulServerApplication.class, args);
    }
}
