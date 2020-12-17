package com.tui.proof.ws.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.service.BookingService;
import com.tui.proof.ws.service.FlightService;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final BookingService bookingService;

    @Override
    public void addFlight(Long bookingId, FlightDto flightDto) {
        Booking booking = bookingService.findBooking(bookingId);
        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDto, flight, "price");

        Monetary monetary = new Monetary();
        BeanUtils.copyProperties(flightDto.getPrice(), monetary);
        flight.setPrice(monetary);
        booking.getFlights().add(flight);
    }

    @Override
    public void deleteFlight(Long bookingId, FlightDto flightDto) {
        Booking booking = bookingService.findBooking(bookingId);
        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDto, flight, "price");

        Monetary monetary = new Monetary();
        BeanUtils.copyProperties(flightDto.getPrice(), monetary);
        flight.setPrice(monetary);

        booking.getFlights().remove(flight);
    }
}
