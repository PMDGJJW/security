package cn.nyse.fsxjjw.Filter;

import cn.nyse.fsxjjw.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Autowired
    private Environment env;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //解决filter无法依赖注入的问题
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt =  WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("env") != null && env == null){
            env = (Environment) cxt.getBean("env");
        }

        if (cxt != null && cxt.getBean("jwtUtil") != null && jwtUtil == null){
            jwtUtil = (JwtUtil) cxt.getBean("jwtUtil");
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
