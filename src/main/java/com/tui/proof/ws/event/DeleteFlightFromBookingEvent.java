package com.tui.proof.ws.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import com.tui.proof.ws.dto.FlightDto;

@Getter
public class DeleteFlightFromBookingEvent extends ApplicationEvent {

    private final long id;
    private final FlightDto flightDto;

    public DeleteFlightFromBookingEvent(long id, FlightDto flightDto) {
        super(id);
        this.id = id;
        this.flightDto = flightDto;
    }
}
