package com.tui.proof.ws.service.impl;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tui.proof.ws.dto.AvailabilityDto;
import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.event.FlightAvailableEvent;
import com.tui.proof.ws.event.dispatcher.EventDispatcher;
import com.tui.proof.ws.repository.FlightRepository;
import com.tui.proof.ws.service.AvailabilityService;

@Service
@AllArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final EventDispatcher eventDispatcher;
    private final FlightRepository flightRepository;

    @Override
    public List<FlightDto> availableFlights(AvailabilityDto availabilityDto) {
        return flightRepository.findAvailableFlights().stream()
                .peek(it -> eventDispatcher.dispatch(new FlightAvailableEvent(it)))
                .map(it -> new FlightDto(
                        it.getCompany(),
                        it.getFlightNumber(),
                        new MonetaryDto(it.getPrice().getAmount(), it.getPrice().getCurrency()),
                        it.getDate(),
                        it.getTime())
                ).collect(Collectors.toList());
    }
}
