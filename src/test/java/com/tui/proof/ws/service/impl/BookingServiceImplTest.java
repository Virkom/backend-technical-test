package com.tui.proof.ws.service.impl;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.dto.HolderDto;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Holder;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.service.BookingService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookingServiceImplTest {

    @Autowired
    private BookingService testSubject;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    void addBooking() {

        Holder holder = new Holder();
        holder.setName("");

        testSubject.addBooking(new BookingDto(new HolderDto(holder), Collections.emptyList()));

        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void deleteBooking() {

        Holder holder = new Holder();
        holder.setName("");

        Mockito.when(bookingRepository.findById(1L)).thenReturn(
                java.util.Optional.of(new Booking(holder, new ArrayList<>(Collections.emptyList())))
        );
        assertAll(() -> testSubject.deleteById(1L));
    }
}
