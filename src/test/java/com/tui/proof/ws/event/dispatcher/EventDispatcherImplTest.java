package com.tui.proof.ws.event.dispatcher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.event.ConfirmBookingEvent;
import com.tui.proof.ws.event.handler.ConfirmBookingEventHandler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EventDispatcherImplTest {

    @Autowired
    private EventDispatcher testSubject;

    @MockBean
    private ConfirmBookingEventHandler eventHandler;

    @Test
    void dispatch() throws InterruptedException {
        testSubject.dispatch(new ConfirmBookingEvent(1L));
        Thread.sleep(1000);
        verify(eventHandler, times(1)).onApplicationEvent(any(ConfirmBookingEvent.class));
    }
}
