package com.tui.proof.ws.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tui.proof.ws.service.MessageService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @Test
    void test() {
        assertEquals(
                "Booking with id 1 not found",
                messageService.getMessage("error.booking.not_found", 1)
        );
    }

}
