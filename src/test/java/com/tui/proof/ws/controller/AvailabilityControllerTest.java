package com.tui.proof.ws.controller;

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
import com.tui.proof.ws.conf.properties.FlightProperties;
import com.tui.proof.ws.dto.AuthenticationRequest;
import com.tui.proof.ws.dto.AuthenticationResponse;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FlightProperties flightProperties;

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
    void availabilityRequestIsInvalid() throws Exception {

        String request = "{" +
                "\"airportOrigin\": \"origin\", " +
                "\"dateFrom\": \"2020-11-20\", " +
                "\"dateTo\": \"2020-11-20\", " +
                "\"paxes\": { \"infants\": 0, \"children\": 0, \"adults\": 0}" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/availability/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.errors[*].cause", containsInAnyOrder("airportDestination")))
                .andExpect(jsonPath("$.errors[*].message", containsInAnyOrder("must not be empty")));
    }

    @Test
    void availabilityRequestIsValid() throws Exception {

        when(flightProperties.getFlightAvailabilityInSeconds()).thenReturn(100);

        String request = "{" +
                "\"airportOrigin\": \"origin\", " +
                "\"airportDestination\": \"destination\", " +
                "\"dateFrom\": \"2020-11-20\", " +
                "\"dateTo\": \"2020-11-20\", " +
                "\"paxes\": { \"infants\": 0, \"children\": 0, \"adults\": 0}" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/availability/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
