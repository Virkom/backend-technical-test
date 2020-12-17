package com.tui.proof.ws.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.conf.properties.FlightProperties;
import com.tui.proof.ws.event.FlightAvailableEvent;
import com.tui.proof.ws.repository.FlightRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlightAvailableEventHandler implements ApplicationListener<FlightAvailableEvent> {

    private final FlightProperties flightProperties;
    private final FlightRepository flightRepository;

    @Override
    public void onApplicationEvent(FlightAvailableEvent flightAvailableEvent) {
        try {
            Thread.sleep(1000 * 60 * flightProperties.getFlightAvailabilityInSeconds());
            flightRepository.deleteFlight(flightAvailableEvent.getFlight());
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
