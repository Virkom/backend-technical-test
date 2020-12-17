package com.tui.proof.ws.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.service.FlightService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class FlightServiceImplTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private FlightService testSubject;

    @Test
    void addFlight() {
        Booking booking = new Booking();
        booking.setFlights(new ArrayList<>());

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        FlightDto flightDto = new FlightDto(
                "",
                "",
                new MonetaryDto(BigDecimal.ONE, ""),
                LocalDate.of(2020, 12, 1),
                LocalTime.of(10, 15)
        );

        Flight flight = new Flight(
                "",
                "",
                new Monetary(BigDecimal.ONE, ""),
                LocalDate.of(2020, 12, 1),
                LocalTime.of(10, 15)
        );

        testSubject.addFlight(1L, flightDto);

        assertTrue(booking.getFlights().contains(flight));
    }

    @Test
    void deleteFlight() {

        Flight flight = new Flight(
                "",
                "",
                new Monetary(BigDecimal.ONE, ""),
                LocalDate.of(2020, 12, 1),
                LocalTime.of(10, 15)
        );

        Booking booking = new Booking();
        booking.setFlights(new ArrayList<>());
        booking.getFlights().add(flight);

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        FlightDto flightDto = new FlightDto(
                "",
                "",
                new MonetaryDto(BigDecimal.ONE, ""),
                LocalDate.of(2020, 12, 1),
                LocalTime.of(10, 15)
        );

        testSubject.deleteFlight(1L, flightDto);

        assertFalse(booking.getFlights().contains(flight));
    }
}
