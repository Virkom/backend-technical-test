package com.tui.proof.ws.conf.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jwt properties from application.yml
 */
@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    /**
     * Jwt secret
     */
    private String secret;
    /**
     * Validity time in seconds
     */
    private int tokenValidityInSeconds;
}
