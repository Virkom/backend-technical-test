package com.tui.proof.ws.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import com.tui.proof.ws.model.Flight;

@Getter
public class FlightAvailableEvent extends ApplicationEvent {

    private final Flight flight;

    public FlightAvailableEvent(Flight flight) {
        super(flight);
        this.flight = flight;
    }

}
