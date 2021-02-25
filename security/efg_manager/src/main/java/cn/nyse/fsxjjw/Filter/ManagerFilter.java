package cn.nyse.fsxjjw.Filter;

import com.netflix.zuul.ZuulFilter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;



/**
 * @auth jian j w
 * @date 2020/8/5 22:56
 * @Description
 */
@Component
@Order(2)
public class ManagerFilter extends ZuulFilter{

    @Autowired
    private Environment env;

    private ManagerFilter(Environment  environment ){
        env = environment;
    }

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();

        String requestedSessionId = request.getRequestedSessionId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal =  authentication.getPrincipal();

        if (principal instanceof User){
            User user = (User) principal;
            if ( user.getAuthorities().size()>0){
                // 不需要将请求转发到后端
                ctx.setSendZuulResponse(true);
                return null;
            }
        }
        ctx.setSendZuulResponse(false);
        return null;
    }
}
