package com.tui.proof.ws.event.handler;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.event.ConfirmBookingEvent;
import com.tui.proof.ws.service.BookingService;

@Component
@AllArgsConstructor
public class ConfirmBookingEventHandler implements ApplicationListener<ConfirmBookingEvent> {

    private final BookingService bookingService;

    @Override
    public void onApplicationEvent(ConfirmBookingEvent confirmBookingEvent) {
        bookingService.confirm(confirmBookingEvent.getBookingId());
    }
}
