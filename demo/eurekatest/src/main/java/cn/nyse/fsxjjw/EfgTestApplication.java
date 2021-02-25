package cn.nyse.fsxjjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EfgTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EfgTestApplication.class,args);
    }

}
