package com.tui.proof.ws.event.handler;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.event.AddBookingEvent;

@Component
@AllArgsConstructor
public class AddBookingEventHandler implements ApplicationListener<AddBookingEvent> {

    @Override
    public void onApplicationEvent(AddBookingEvent addBookingEvent) {
        // place any code here to react to adding a booking
    }
}
