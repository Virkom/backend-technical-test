package com.tui.proof.ws.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import com.tui.proof.ws.dto.BookingDto;

@Getter
public class AddBookingEvent extends ApplicationEvent {

    private final BookingDto bookingDto;

    public AddBookingEvent(BookingDto bookingDto) {
        super(bookingDto);
        this.bookingDto = bookingDto;
    }
}
