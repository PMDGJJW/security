package cn.nyse.fsxjjw.config.handler;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();


//    @Autowired
//    private void sessionRegistry(SessionRegistry sessionRegistry){
//        UserAuthenticationFilter.sessionRegistry = sessionRegistry;
//    };

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
        SessionRegistry sessionRegistry = SpringContextUtil.getBean(SessionRegistry.class);
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // 注册session
        sessionRegistry.registerNewSession(request.getSession().getId(), authRequest.getPrincipal());

        List<Object> allPrincipals =  sessionRegistry.getAllPrincipals();
            for (Object allPrincipal : allPrincipals) {
                List<SessionInformation> allSessions = sessionRegistry.getAllSessions(allPrincipal, false);
                if (allSessions.size()>0){
                    for (SessionInformation allSession : allSessions) {
                        if (request.getSession().getId() != null && username.equals(allSession.getPrincipal())){
                            if (allSession.getSessionId() != request.getSession().getId() ){
                                allSession.expireNow();
                            }
                        }
                    }
                }
            }

            this.setDetails(request, authRequest);

        return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = this.getBodyParams(request).get(super.SPRING_SECURITY_FORM_PASSWORD_KEY);
        if(!StringUtils.isEmpty(password)){
            return password;
        }
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = this.getBodyParams(request).get(super.SPRING_SECURITY_FORM_USERNAME_KEY);
        if(!StringUtils.isEmpty(username)){
            return username;
        }
        return super.obtainUsername(request);
    }



    private Map<String,String> getBodyParams(HttpServletRequest request){
        Map<String,String> bodyParams =  threadLocal.get();
        if(bodyParams==null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try (InputStream is = request.getInputStream()) {
                bodyParams = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
            }
            if(bodyParams==null) bodyParams = new HashMap<>();
            threadLocal.set(bodyParams);
        }
        return bodyParams;
    }


}
