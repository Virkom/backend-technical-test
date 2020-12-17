package com.tui.proof.ws.repository;

import java.util.Optional;

import com.tui.proof.ws.model.Booking;

/**
 * Repository for booking operations
 */
public interface BookingRepository {

    /**
     * Save booking details
     * @param booking booking details
     * @return saved booking
     */
    Booking save(Booking booking);

    /**
     * Find booking by Id
     * @param id of booking
     * @return booking details if found
     */
    Optional<Booking> findById(Long id);

    /**
     * Delete booking by Id
     * @param booking of booking
     */
    void deleteById(Booking booking);
}
