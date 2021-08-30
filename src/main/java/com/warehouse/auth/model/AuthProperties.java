package com.warehouse.auth.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
    private String[] whiteList;

    private AuthJwt jwt;


    @Data
    public static class AuthJwt{
        private String key;

        private String prefix;

        private int expiration;
    }
}
