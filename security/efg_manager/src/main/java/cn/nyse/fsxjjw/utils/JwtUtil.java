package cn.nyse.fsxjjw.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @auth jian j w
 * @date 2020/5/15 16:57
 * @Description
 */
@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {

    private String key ;         //秘钥

    private long exp ;          //过期时间

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public String createJWT(String id, String subject, User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .setIssuer(subject)
                .signWith(SignatureAlgorithm.HS256, key)
        .claim("user", user);
        if (exp > 0) {
            builder.setExpiration( new Date( nowMillis + exp));
        }
        return builder.compact();
    }



    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public  Claims parseJWT(String jwtStr)  {

//        try {
            return  Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwtStr)
                    .getBody();
//        } catch (Exception e) {
//            throw e;
//        }
    }

}

