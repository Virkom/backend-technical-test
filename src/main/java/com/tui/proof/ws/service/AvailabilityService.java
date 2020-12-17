package com.tui.proof.ws.service;

import java.util.List;

import com.tui.proof.ws.dto.AvailabilityDto;
import com.tui.proof.ws.dto.FlightDto;

/**
 * Service for availability operations
 */
public interface AvailabilityService {

    /**
     * Get available flights
     * @param availabilityDto availability request
     * @return list of available flights
     */
    List<FlightDto> availableFlights(AvailabilityDto availabilityDto);
}
