package com.tui.proof.ws.event.handler;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.event.DeleteBookingEvent;
import com.tui.proof.ws.service.BookingService;

@Component
@AllArgsConstructor
public class DeleteBookingEventHandler implements ApplicationListener<DeleteBookingEvent> {

    private final BookingService bookingService;

    @Override
    public void onApplicationEvent(DeleteBookingEvent deleteBookingEvent) {
        bookingService.deleteById(deleteBookingEvent.getBookingId());
    }
}
