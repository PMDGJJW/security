package com.pmdgjjw.sso.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginText {

    @RequestMapping("/oauth")
    public Object checkOauth(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "code",required = false) String code) {



        return "111";
    }

    @RequestMapping("/test")
    public Object test() {
        return "hhhhhhhh";
    }
}
