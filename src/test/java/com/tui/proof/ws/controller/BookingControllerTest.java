package com.tui.proof.ws.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
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
import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.HolderDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Holder;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.repository.FlightRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

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
    void createBooking() throws Exception {

        FlightDto flightDto = new FlightDto(
                "company",
                "flightNumber",
                new MonetaryDto(
                        new BigDecimal(100),
                        "USD"
                ),
                LocalDate.now(),
                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
        );

        Holder holder = new Holder();
        holder.setName("name");
        holder.setLastName("lastName");
        holder.setAddress("address");
        holder.setPostalCode("postalCode");
        holder.setCountry("country");
        holder.setEmail("mail@example.com");
        holder.setTelephones(List.of("1234567890"));

        BookingDto booking = new BookingDto(
                new HolderDto(holder),
                List.of(flightDto));

        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDto, flight, "price");

        Monetary monetary = new Monetary();
        BeanUtils.copyProperties(flightDto.getPrice(), monetary);

        flight.setPrice(monetary);
        when(flightRepository.isAvailable(flight)).thenReturn(true);

        String request = objectMapper.writeValueAsString(booking);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/booking/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
        ;
    }

    @Test
    void getBooking() throws Exception {

        Holder holder = new Holder();
        holder.setName("name");
        holder.setLastName("lastName");
        holder.setAddress("address");
        holder.setPostalCode("postalCode");
        holder.setCountry("country");
        holder.setEmail("mail@example.com");
        holder.setTelephones(List.of("1234567890"));

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(new Booking(holder, Collections.emptyList())));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    void deleteBooking() throws Exception {

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(new Booking(null, Collections.emptyList())));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/booking/1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void confirm() throws Exception {
        when(flightRepository.isAvailable(any(Flight.class))).thenReturn(true);

        Holder holder = new Holder();
        holder.setName("name");
        holder.setLastName("lastName");
        holder.setAddress("address");
        holder.setPostalCode("postalCode");
        holder.setCountry("country");
        holder.setEmail("mail@example.com");
        holder.setTelephones(List.of("1234567890"));

        when(bookingRepository.findById(1L)).thenReturn(
                Optional.of(new Booking(holder, List.of(new Flight(
                        "",
                        "",
                        new Monetary(BigDecimal.ONE, ""),
                        LocalDate.now(),
                        LocalTime.now())
                ))));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/booking/1/confirm")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
