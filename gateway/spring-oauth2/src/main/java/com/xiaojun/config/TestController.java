package com.xiaojun.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojun
 * @date 2018/12/1 22:16
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/info/test")
    public String test() {
        log.info("success");
        return "success";
    }
}
