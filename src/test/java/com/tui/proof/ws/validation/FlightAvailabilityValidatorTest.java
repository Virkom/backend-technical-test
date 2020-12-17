package com.tui.proof.ws.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.repository.FlightRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FlightAvailabilityValidatorTest {

    @Autowired
    private FlightAvailabilityValidator flightAvailabilityValidator;

    @MockBean
    private FlightRepository flightRepository;

    @Test
    void validate() {
        FlightDto flightDto = new FlightDto(
                "company",
                "flNum",
                new MonetaryDto(new BigDecimal(1), "USD"),
                LocalDate.now(),
                LocalTime.now()
        );

        when(flightRepository.isAvailable(any())).thenReturn(true);

        assertTrue(flightAvailabilityValidator.isValid(flightDto, null));
    }

}
