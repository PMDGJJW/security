package com.pmdgjjw.sso.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class SsoController {

    @RequestMapping("/getUser")
    private Object test(Authentication authentication, HttpServletRequest request){
        String header = request.getHeader("authentication");
       return header;
    }
}
