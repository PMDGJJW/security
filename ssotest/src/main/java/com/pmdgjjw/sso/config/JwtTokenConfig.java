package com.pmdgjjw.sso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

@Configuration
public class JwtTokenConfig {

        //TokenStore
        @Bean
        public TokenStore tokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }


        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            //Jwt密钥
            jwtAccessTokenConverter.setSigningKey("EFGFlight");
            return jwtAccessTokenConverter;
        }

        //创建JWTToken扩展信息Bean
        @Bean
        public JwtTokenEnhancer jwtTokenEnhancer(){
            return new JwtTokenEnhancer();
        }

}
