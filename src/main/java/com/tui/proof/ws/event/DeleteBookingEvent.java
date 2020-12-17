package com.tui.proof.ws.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteBookingEvent extends ApplicationEvent {

    private final long bookingId;

    public DeleteBookingEvent(long bookingId) {
        super(bookingId);
        this.bookingId = bookingId;
    }
}
