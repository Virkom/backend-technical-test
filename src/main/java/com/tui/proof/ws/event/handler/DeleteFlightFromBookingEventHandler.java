package com.tui.proof.ws.event.handler;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.event.DeleteFlightFromBookingEvent;
import com.tui.proof.ws.service.FlightService;

@Component
@AllArgsConstructor
public class DeleteFlightFromBookingEventHandler implements ApplicationListener<DeleteFlightFromBookingEvent> {

    private final FlightService flightService;

    @Override
    public void onApplicationEvent(DeleteFlightFromBookingEvent deleteFlightFromBookingEvent) {
        flightService.deleteFlight(deleteFlightFromBookingEvent.getId(), deleteFlightFromBookingEvent.getFlightDto());
    }
}
