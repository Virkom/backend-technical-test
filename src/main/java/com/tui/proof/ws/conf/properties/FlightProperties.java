package com.tui.proof.ws.conf.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Flight properties from application.yml
 */
@Data
@Component
@ConfigurationProperties("flight")
public class FlightProperties {

    /**
     * Flight availability in seconds
     */
    private int flightAvailabilityInSeconds;
}
