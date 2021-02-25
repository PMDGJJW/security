package com.pmdgjjw.sso.demo.utils;//package com.pmdgjjw.sso.demo.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
///**
// * @auth jian j w
// * @date 2020/5/15 16:57
// * @Description
// */
//@ConfigurationProperties("jwt.config")
//@Component
//public class JwtUtil {
//
//    private String key ;         //秘钥
//
//    private long exp ;          //过期时间
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public long getExp() {
//        return exp;
//    }
//
//    public void setExp(long exp) {
//        this.exp = exp;
//    }
//
//    /**
//     * 生成JWT
//     *
//     * @param id
//     * @param subject
//     * @return
//     */
//    public String createJWT(String id, String subject) {
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        JwtBuilder builder = Jwts.builder().setId(id)
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .setIssuer(subject)
//                .signWith(SignatureAlgorithm.HS256, key);
//        if (exp > 0) {
//            builder.setExpiration( new Date( nowMillis + exp));
//        }
//        return builder.compact();
//    }
//
//
//
//    /**
//     * 解析JWT
//     * @param jwtStr
//     * @return
//     */
//    public  Claims parseJWT(String jwtStr)  {
////        try {
//        Claims body = Jwts.parser().setSigningKey(key.getBytes(StandardCharsets.UTF_8))
//                .parseClaimsJws(jwtStr)
//                .getBody();
//        return body;
////        } catch (Exception e) {
////            throw e;
////        }
//    }
//
//}
//
