package com.tui.proof.ws.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import com.tui.proof.ws.dto.FlightDto;

@Getter
public class AddFlightToBookingEvent extends ApplicationEvent {

    private final Long id;
    private final FlightDto flightDto;

    public AddFlightToBookingEvent(Long id, FlightDto flightDto) {
        super(id);
        this.id = id;
        this.flightDto = flightDto;
    }
}
