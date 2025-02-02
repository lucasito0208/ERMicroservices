package com.lucasDev.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class JwtConfig {

    private String secret;

    private long expiration;
    
}
