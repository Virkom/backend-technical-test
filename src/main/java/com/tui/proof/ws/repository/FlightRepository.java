package com.tui.proof.ws.repository;

import java.util.List;

import com.tui.proof.ws.model.Flight;

/**
 * Repository for flight operations
 */
public interface FlightRepository {

    /**
     * Find available flights
     * @return list of available flights
     */
    List<Flight> findAvailableFlights();

    /**
     * Check if flight available to use
     * @param flight flight details
     * @return true if flight available, false if not
     */
    boolean isAvailable(Flight flight);

    /**
     * Delete flight from list of available
     * @param flight flight details
     */
    void deleteFlight(Flight flight);
}
