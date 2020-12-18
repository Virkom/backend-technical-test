package com.tui.proof.ws.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.dto.AuthenticationRequest;
import com.tui.proof.ws.dto.AuthenticationResponse;
import com.tui.proof.ws.dto.AvailabilityDto;
import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.HolderDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.dto.PaxesDto;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createBookingWithAvailableFlight_thenCreated() throws Exception {

        // authenticate
        String authResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(objectMapper.writeValueAsString(new AuthenticationRequest("admin", "12345678")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        final String jwtToken = objectMapper.readValue(authResponse, AuthenticationResponse.class).getAccessToken();

        // get available flight
        AvailabilityDto availabilityDto =
                new AvailabilityDto(
                        "origin",
                        "destination",
                        LocalDate.of(2020, 12, 18),
                        LocalDate.of(2020, 12, 20),
                        new PaxesDto(0, 0, 0)
                );

        String availabilityResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/availability/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .content(objectMapper.writeValueAsString(availabilityDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JavaType type = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Flight.class);
        final List<Flight> flights = objectMapper.readValue(availabilityResponse, type);

        // prepare add a booking request
        HolderDto holderDto = new HolderDto();
        holderDto.setName("test_name");
        holderDto.setLastName("test_lastName");
        holderDto.setCountry("test_country");
        holderDto.setPostalCode("123456");
        holderDto.setAddress("test_address");
        holderDto.setEmail("mail@example.com");
        holderDto.setTelephones(List.of("1234567890"));

        Flight flight = flights.stream().findAny().orElseThrow();
        FlightDto flightDto =
                new FlightDto(
                        flight.getCompany(),
                        flight.getFlightNumber(),
                        new MonetaryDto(flight.getPrice().getAmount(), flight.getPrice().getCurrency()),
                        flight.getDate(),
                        flight.getTime()
                );

        BookingDto bookingDto = new BookingDto(holderDto, List.of(flightDto));

        // add booking
        String request = objectMapper.writeValueAsString(bookingDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/booking/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void createBookingWithNoAvailableFlight_thenCreated() throws Exception {

        // authenticate
        String authResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(objectMapper.writeValueAsString(new AuthenticationRequest("admin", "12345678")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        final String jwtToken = objectMapper.readValue(authResponse, AuthenticationResponse.class).getAccessToken();

        // prepare add a booking request
        HolderDto holderDto = new HolderDto();
        holderDto.setName("test_name");
        holderDto.setLastName("test_lastName");
        holderDto.setCountry("test_country");
        holderDto.setPostalCode("123456");
        holderDto.setAddress("test_address");
        holderDto.setEmail("mail@example.com");
        holderDto.setTelephones(List.of("1234567890"));

        Flight flight = new Flight(
                "test_company",
                "test_number",
                new Monetary(BigDecimal.ONE, "USD"),
                LocalDate.now(),
                LocalTime.now()
        );


        FlightDto flightDto =
                new FlightDto(
                        flight.getCompany(),
                        flight.getFlightNumber(),
                        new MonetaryDto(flight.getPrice().getAmount(), flight.getPrice().getCurrency()),
                        flight.getDate(),
                        flight.getTime()
                );

        BookingDto bookingDto = new BookingDto(holderDto, List.of(flightDto));

        // add booking
        String request = objectMapper.writeValueAsString(bookingDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/booking/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.errors[*].cause", containsInAnyOrder("flights[0]")))
                .andExpect(jsonPath("$.errors[*].message", containsInAnyOrder("Flight is not available")));
    }

}
