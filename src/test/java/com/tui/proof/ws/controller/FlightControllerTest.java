package com.tui.proof.ws.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.dto.AuthenticationRequest;
import com.tui.proof.ws.dto.AuthenticationResponse;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.repository.FlightRepository;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private FlightRepository flightRepository;

    private String token;


    @BeforeEach
    void setUp() throws Exception {
        final String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(objectMapper.writeValueAsString(new AuthenticationRequest("admin", "12345678")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        token = objectMapper.readValue(response, AuthenticationResponse.class).getAccessToken();
    }

    @Test
    void addFlight() throws Exception {
        Flight flight = new Flight(
                "company",
                UUID.randomUUID().toString(),
                new Monetary(
                        new BigDecimal(100),
                        "USD"
                ),
                LocalDate.now(),
                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
        );

        when(flightRepository.isAvailable(flight)).thenReturn(true);
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(new Booking(null, Collections.emptyList())));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/booking/1/flight")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(flight))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deleteFlight() throws Exception {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(new Booking(null, Collections.emptyList())));

        Flight flight = new Flight(
                "company",
                UUID.randomUUID().toString(),
                new Monetary(
                        new BigDecimal(100),
                        "USD"
                ),
                LocalDate.now(),
                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
        );

        when(flightRepository.isAvailable(flight)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/booking/1/flight")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(flight))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
