package com.tui.proof.ws.event.handler;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.event.AddFlightToBookingEvent;
import com.tui.proof.ws.service.FlightService;

@Component
@AllArgsConstructor
public class AddFlightToBookingEventHandler implements ApplicationListener<AddFlightToBookingEvent> {

    private final FlightService flightService;

    @Override
    public void onApplicationEvent(AddFlightToBookingEvent addFlightToBookingEvent) {
        flightService.addFlight(addFlightToBookingEvent.getId(), addFlightToBookingEvent.getFlightDto());
    }
}
