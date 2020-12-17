package com.tui.proof.ws.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tui.proof.ws.dto.AvailabilityDto;
import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.PaxesDto;
import com.tui.proof.ws.event.FlightAvailableEvent;
import com.tui.proof.ws.event.dispatcher.EventDispatcher;
import com.tui.proof.ws.repository.impl.FlightRepositoryImpl;
import com.tui.proof.ws.service.AvailabilityService;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceImplTest {

    @Mock
    private EventDispatcher eventDispatcher;

    @Test
    void availableFlights() throws Exception {

        AvailabilityService testSubject = new AvailabilityServiceImpl(eventDispatcher, new FlightRepositoryImpl());

        AvailabilityDto availabilityDto =
                new AvailabilityDto(
                        "",
                        "",
                        LocalDate.now(),
                        LocalDate.now(),
                        new PaxesDto(1,1, 1)
                );

        List<FlightDto> result = testSubject.availableFlights(availabilityDto);
        Thread.sleep(1000); //wait for event dispatcher completed
        Mockito.verify(eventDispatcher, Mockito.times(result.size())).dispatch(any(FlightAvailableEvent.class));

    }
}
