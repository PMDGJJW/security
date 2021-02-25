package cn.nyse.fsxjjw.config.handler;

import cn.nyse.fsxjjw.common.entity.JsonResult;
import cn.nyse.fsxjjw.common.utils.ResultTool;
import cn.nyse.fsxjjw.entity.SysUser;
import cn.nyse.fsxjjw.serivce.SysUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserService sysUserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

            //更新用户表上次登录时间、更新人、更新时间等字段
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            SysUser sysUser = sysUserService.selectByUserName(userDetails.getUsername());

            sysUser.setLastLoginTime(new Date());
            sysUser.setUpdateTime(new Date());
            sysUser.setUpdateUser(sysUser.getId());
            sysUserService.update(sysUser);

            //返回json数据
            JsonResult result = ResultTool.success();
            //处理编码方式，防止中文乱码的情况
            httpServletResponse.setContentType("text/json;charset=utf-8");
            httpServletResponse.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

            PrintWriter writer = httpServletResponse.getWriter();

            writer.write(JSON.toJSONString(result));
        }

}
