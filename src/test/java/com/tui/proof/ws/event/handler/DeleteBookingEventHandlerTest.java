package com.tui.proof.ws.event.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.event.DeleteBookingEvent;
import com.tui.proof.ws.service.BookingService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DeleteBookingEventHandlerTest {

    @Autowired
    private DeleteBookingEventHandler eventHandler;

    @MockBean
    private BookingService bookingService;

    @Test
    void handle() {
        eventHandler.onApplicationEvent(new DeleteBookingEvent(1L));
        verify(bookingService, times(1)).deleteById(1L);
    }

}
