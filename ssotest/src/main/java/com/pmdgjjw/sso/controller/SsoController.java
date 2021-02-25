package com.pmdgjjw.sso.controller;

import com.pmdgjjw.sso.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class SsoController {

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/getUser")
    private Object test(Authentication authentication, HttpServletRequest request){
        String header = request.getHeader("authentication");
        String bearer = header.substring(header.lastIndexOf("bearer") + 7);

        Claims body = Jwts.parser().setSigningKey("EFGFlight".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(bearer)
                .getBody();
        return body;
    }
}
