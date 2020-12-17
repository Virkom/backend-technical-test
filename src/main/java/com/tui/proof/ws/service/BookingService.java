package com.tui.proof.ws.service;

import com.tui.proof.ws.dto.BookingAdditionalDto;
import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.model.Booking;

/**
 * Service for booking operations
 */
public interface BookingService {

    /**
     * Find booking by Id
     * @param id of booking
     * @return booking details
     */
    BookingAdditionalDto findById(Long id);

    /**
     * Find booking by Id
     * @param id of booking
     * @return booking details
     */
    Booking findBooking(Long id);

    /**
     * Add a booking
     * @param request booking details
     */
    void addBooking(BookingDto request);

    /**
     * Delete booking by Id
     * @param id of booking
     */
    void deleteById(Long id);

    /**
     * Confirm booking by Id
     * @param id of booking
     */
    void confirm(Long id);
}
