package com.tui.proof.ws.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.repository.FlightRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlightRepositoryImplTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void findAllFlights() {
        assertTrue(flightRepository.findAvailableFlights().size() > 0);
    }

    @Test
    void isAvailable() {
        Flight flight = flightRepository.findAvailableFlights().get(0);
        assertTrue(flightRepository.isAvailable(flight));
    }

    @Test
    void delete() {
        Flight flight = flightRepository.findAvailableFlights().get(0);
        flightRepository.deleteFlight(flight);
        assertFalse(flightRepository.isAvailable(flight));
    }
}
