package com.tui.proof.ws.repository.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.repository.BookingRepository;

@Service
public class BookingRepositoryImpl implements BookingRepository {

    private final static List<Booking> bookingStorage = new ArrayList<>();

    @Override
    public Booking save(Booking booking) {
        long nextId = bookingStorage.stream()
                .max(Comparator.comparing(Booking::getId))
                .map(it -> it.getId() + 1)
                .orElse(0L);

        booking.setId(nextId);
        bookingStorage.add(booking);

        return booking;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingStorage.stream()
                .filter(it -> id.equals(it.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(Booking booking) {
        bookingStorage.removeIf(it -> booking.getId().equals(it.getId()));
    }
}
