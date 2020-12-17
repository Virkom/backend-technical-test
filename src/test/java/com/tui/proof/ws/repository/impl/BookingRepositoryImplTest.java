package com.tui.proof.ws.repository.impl;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Holder;
import com.tui.proof.ws.repository.BookingRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingRepositoryImplTest {

    private final BookingRepository bookingRepository = new BookingRepositoryImpl();

    @Test
    void save() {
        Holder holder = new Holder();
        holder.setName("TEST_NAME");

        Booking booking = new Booking();
        booking.setConfirmed(true);
        booking.setHolder(holder);
        booking.setFlights(Collections.emptyList());

        bookingRepository.save(booking);

        assertEquals(0L, bookingRepository.findById(0L).orElseThrow().getId());
        assertEquals("TEST_NAME", bookingRepository.findById(0L).orElseThrow().getHolder().getName());
    }

    @Test
    void delete() {
        Holder holder = new Holder();
        holder.setName("TEST_NAME");

        Booking booking = new Booking();
        booking.setConfirmed(true);
        booking.setHolder(holder);
        booking.setFlights(Collections.emptyList());

        bookingRepository.save(booking);

        assertEquals(0L, bookingRepository.findById(0L).orElseThrow().getId());
        assertEquals("TEST_NAME", bookingRepository.findById(0L).orElseThrow().getHolder().getName());

        bookingRepository.deleteById(booking);

        assertTrue(bookingRepository.findById(0L).isEmpty());
    }

}
