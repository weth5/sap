package com.whx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by Administrator on 2019/7/24.
 */
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class Springboot {
    public static void main(String[] args) {
        SpringApplication.run(Springboot.class,args);
    }
}
