package com.tui.proof.ws.event.handler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.event.AddFlightToBookingEvent;
import com.tui.proof.ws.service.FlightService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AddFlightToBookingEventHandlerTest {

    @Autowired
    private AddFlightToBookingEventHandler eventHandler;

    @MockBean
    private FlightService flightService;

    @Test
    void handle() {
        FlightDto flightDto = new FlightDto(
                "",
                "",
                new MonetaryDto(new BigDecimal(1), "USD"),
                LocalDate.now(),
                LocalTime.now()
        );
        eventHandler.onApplicationEvent(new AddFlightToBookingEvent(1L, flightDto));
        verify(flightService, times(1)).addFlight(1L, flightDto);
    }
}
