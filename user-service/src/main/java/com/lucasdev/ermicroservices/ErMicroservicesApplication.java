package com.lucasdev.ermicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties("com.ermicroservices.security")
public class ErMicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErMicroservicesApplication.class, args);
    }

}
