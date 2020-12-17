package com.tui.proof.ws.conf.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableConfigurationProperties(value = FlightProperties.class)
@SpringBootTest
class FlightPropertiesTest {

    @Autowired
    private FlightProperties flightProperties;

    @Test
    void flightPropertiesWasLoaded() {
        assertEquals(0, flightProperties.getFlightAvailabilityInSeconds());
    }

}
