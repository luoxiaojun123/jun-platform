package com.xiaojun.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author long.luo
 * @date 2018/8/21 10:02
 */
@RestController
@Slf4j
public class DemoController {

    /**
     * access_token 认证服务器获取
     * http://localhost:9002/hello?access_token=436a56fb-f189-49eb-8429-bf56a62aa941
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello oauth";
    }
}
