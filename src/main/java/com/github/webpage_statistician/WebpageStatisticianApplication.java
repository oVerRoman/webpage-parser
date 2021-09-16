package com.github.webpage_statistician;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class WebpageStatisticianApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebpageStatisticianApplication.class, args);
    }
}