package com.xiaojun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author long.luo
 * @date 2018/9/12 20:02
 */
@RestController
public class ProvideController {

    /**
     * 获取信息
     *
     * @return
     */
    @GetMapping("/info")
    public String info(@RequestParam("userName") String userName) {
        return userName + "调用成功了";
    }
}
