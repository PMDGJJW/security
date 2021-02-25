package cn.nyse.fsxjjw.config.handler;


import org.springframework.core.annotation.Order;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter  {


//    private static Environment env;
//    private static JwtUtil jwtUtil;
//    private static SysUserService sysUserService;
//    private static SysPermissionService sysPermissionService;
//    private static SessionRegistry sessionRegistry;


//    @Autowired
//    private void getEnvironment(Environment env){
//        JwtAuthenticationTokenFilter.env = env;
//    }
//
//    @Autowired
//    private void getJwtUtil(JwtUtil jwtUtil){
//        JwtAuthenticationTokenFilter.jwtUtil = jwtUtil;
//    }
//
//    @Autowired
//    private void getSysUserService(SysUserService sysUserService){
//        JwtAuthenticationTokenFilter.sysUserService = sysUserService;
//    }
//
//    @Autowired
//    private void getSysPermissionService(SysPermissionService sysPermissionService){
//        JwtAuthenticationTokenFilter.sysPermissionService = sysPermissionService;
//    }

//    @Bean(name = "SessionRegistry")
//    public SessionRegistry getSessionRegistry(){
//        SessionRegistry sessionRegistry=new SessionRegistryImpl();
//        return sessionRegistry;
//    }
//
//    @Autowired
//    private void sessionRegistry(SessionRegistry sessionRegistry){
//        JwtAuthenticationTokenFilter.sessionRegistry = sessionRegistry;
//    };


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        SessionRegistry sessionRegistry = SpringContextUtil.getBean(SessionRegistry.class);

        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

//        // token
//        String auth = httpServletRequest.getHeader("auth");
//
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//
//        //权限用于构建登录用户
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        // 前缀
//        String prefix = "";
//        String token = "";
//        // token
//        if (!StringUtils.isEmpty(auth)){
//            prefix = env.getProperty("jwt.config.prefix");
//            token  = auth.substring(prefix.length());
//            Claims claims = null;
//            try {
//                // 解析token
//                claims = jwtUtil.parseJWT(token);
//            } catch (Exception e) {
//                // token 过期
//                JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
//                httpServletResponse.getWriter().write(JSON.toJSONString(result));
//            }
//            // token中的用户名
//            Object sysUser = claims.get("user");
//
//                if (sysUser == null) {
//                    //用户不存在
//                    JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
//                    httpServletResponse.getWriter().write(JSON.toJSONString(result));
//                }
//
//                if (sysUser != null){
//
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser,null,grantedAuthorities);
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//            }
//
//        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }


}
