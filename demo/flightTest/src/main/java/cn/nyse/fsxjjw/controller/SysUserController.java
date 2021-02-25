package cn.nyse.fsxjjw.controller;

import cn.nyse.fsxjjw.entity.A;
import cn.nyse.fsxjjw.entity.B;
import cn.nyse.fsxjjw.entity.Test;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
public class SysUserController {

    @GetMapping("/addUser")
    public Object test(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        return attributeNames;
    }

    @GetMapping("/deleteRole")
    public String getRole() {
        return ("模拟删除用户");
    }

}
