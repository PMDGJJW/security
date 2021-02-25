package cn.nyse.fsxjjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EFGCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EFGCenterApplication.class,args);
    }

}
