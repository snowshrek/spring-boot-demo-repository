package com.example.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by David on 2019/10/14.
 */
@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.domain"})
public class DemoSpringbootApplicationTest2 extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoSpringbootApplicationTest2.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringbootApplicationTest2.class, args);
    }

}