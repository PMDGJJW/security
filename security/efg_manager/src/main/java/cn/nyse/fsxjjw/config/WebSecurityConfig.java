package cn.nyse.fsxjjw.config;

import cn.nyse.fsxjjw.config.handler.*;
import cn.nyse.fsxjjw.config.service.Impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;


/**
 * @Author: Hutengfei
 * @Description:
 * @Date Create in 2019/8/28 20:15
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailServiceImpl();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;
    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;
    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    @Autowired
    private SessionRegistry sessionRegistry;

    //访问决策管理器
    @Autowired
    CustomizeAccessDecisionManager accessDecisionManager;

    //实现权限拦截
    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;


    @Autowired
    private CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式等
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        //http相关的配置，包括登入登出、异常处理、会话管理等
        http.authorizeRequests().
                antMatchers("/oauth/**","/login/**","/logout/**").permitAll().and().
                authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//访问决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                }).
         anyRequest().authenticated().
         and().formLogin().permitAll().
         successHandler(customizeAuthenticationSuccessHandler).
         failureHandler(customizeAuthenticationFailureHandler).
        //登出
        and().logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(customizeLogoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID").
        and().exceptionHandling().
                // 自定义异常处理(权限拒绝、登录失效等)
                authenticationEntryPoint(customizeAuthenticationEntryPoint)
        ;
        //配置自定义过滤器 增加post json 支持
        http.addFilterAt(UserAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
//session并发控制过滤器
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry,customizeSessionInformationExpiredStrategy), ConcurrentSessionFilter.class);
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);//增加到默认拦截链中
        ;

    }



    @Bean
    public SessionRegistry sessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }

    // 修改登录信息从body获取
    private UserAuthenticationFilter UserAuthenticationFilterBean() throws Exception {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter();
        userAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        userAuthenticationFilter.setAuthenticationSuccessHandler(customizeAuthenticationSuccessHandler);
        userAuthenticationFilter.setAuthenticationFailureHandler(customizeAuthenticationFailureHandler);
        userAuthenticationFilter.setSessionAuthenticationStrategy(new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry));
        return userAuthenticationFilter;
    }

    // 修改登录信息从body获取
    private JwtAuthenticationTokenFilter jwt() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

}
