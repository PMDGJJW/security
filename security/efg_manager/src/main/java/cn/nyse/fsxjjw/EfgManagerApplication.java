package cn.nyse.fsxjjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "cn.nyse.fsxjjw.dao")
@EnableCircuitBreaker
@EnableZuulProxy
public class EfgManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EfgManagerApplication.class,args);
    }


}
