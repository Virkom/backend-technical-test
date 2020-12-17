package com.tui.proof.ws.event.handler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.event.FlightAvailableEvent;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.FlightRepository;

@SpringBootTest
class FlightAvailableEventHandlerTest {

    @Autowired
    private FlightAvailableEventHandler flightAvailableEventHandler;

    @MockBean
    private FlightRepository flightRepository;

    @Test
    void handle() throws Exception {
        Flight flight = new Flight(
                "",
                "",
                new Monetary(BigDecimal.ONE, ""),
                LocalDate.now(),
                LocalTime.now()
        );

        flightAvailableEventHandler.onApplicationEvent(new FlightAvailableEvent(flight));
        Thread.sleep(1000);
        Mockito.verify(flightRepository).deleteFlight(flight);
    }
}
