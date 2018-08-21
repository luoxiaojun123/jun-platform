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

    @GetMapping("/hello")
    public String hello() {
        return "hello oauth";
    }
}
