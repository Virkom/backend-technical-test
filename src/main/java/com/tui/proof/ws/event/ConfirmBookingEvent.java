package com.tui.proof.ws.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

@Getter
public class ConfirmBookingEvent extends ApplicationEvent {

    private final long bookingId;

    public ConfirmBookingEvent(long bookingId) {
        super(bookingId);
        this.bookingId = bookingId;
    }
}
