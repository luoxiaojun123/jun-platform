package com.xiaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author long.luo
 * @date 2018/8/6 14:48
 */
@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
