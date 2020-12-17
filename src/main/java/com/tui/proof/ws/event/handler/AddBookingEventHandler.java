package com.tui.proof.ws.event.handler;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.event.AddBookingEvent;
import com.tui.proof.ws.service.BookingService;

@Component
@AllArgsConstructor
public class AddBookingEventHandler implements ApplicationListener<AddBookingEvent> {

    private final BookingService bookingService;

    @Override
    public void onApplicationEvent(AddBookingEvent addBookingEvent) {
        bookingService.addBooking(addBookingEvent.getBookingDto());
    }
}
