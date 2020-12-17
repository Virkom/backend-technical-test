package com.tui.proof.ws.event.handler;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.dto.HolderDto;
import com.tui.proof.ws.event.AddBookingEvent;
import com.tui.proof.ws.service.BookingService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AddBookingEventHandlerTest {

    @Autowired
    private AddBookingEventHandler eventHandler;

    @MockBean
    private BookingService bookingService;

    @Test
    void handle() {
        BookingDto bookingDto = new BookingDto(new HolderDto(), Collections.emptyList());
        eventHandler.onApplicationEvent(new AddBookingEvent(bookingDto));
        verify(bookingService, times(1)).addBooking(bookingDto);
    }

}
