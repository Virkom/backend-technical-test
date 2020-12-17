package com.tui.proof.ws.service;

import com.tui.proof.ws.dto.FlightDto;

/**
 * Service for flight operations
 */
public interface FlightService {

    /**
     * Add flight to a booking
     * @param bookingId id of booking
     * @param flightDto flight details
     */
    void addFlight(Long bookingId, FlightDto flightDto);

    /**
     * Delete flight from a booking
     * @param bookingId id of booking
     * @param flightDto flight details
     */
    void deleteFlight(Long bookingId, FlightDto flightDto);
}
