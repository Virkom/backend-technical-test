package com.tui.proof.ws.service.impl;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tui.proof.ws.dto.BookingAdditionalDto;
import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.dto.HolderDto;
import com.tui.proof.ws.dto.MonetaryDto;
import com.tui.proof.ws.exception.ObjectNotFoundException;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Holder;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.service.BookingService;
import com.tui.proof.ws.service.MessageService;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final MessageService messages;
    private final BookingRepository bookingRepository;

    @Override
    public void addBooking(BookingDto request) {
        Holder holder = new Holder();
        BeanUtils.copyProperties(request.getHolder(), holder);

        List<Flight> flights = request.getFlights().stream()
                .map(it -> {
                    Flight flight = new Flight();
                    BeanUtils.copyProperties(it, flight, "price");

                    Monetary monetary = new Monetary();
                    BeanUtils.copyProperties(it.getPrice(), monetary);

                    flight.setPrice(monetary);
                    return flight;
                }).collect(Collectors.toList());

        bookingRepository.save(new Booking(holder, flights));
    }

    @Override
    public BookingAdditionalDto findById(Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    HolderDto holderDto = new HolderDto(booking.getHolder());

                    List<FlightDto> flightDtoList = booking.getFlights().stream()
                            .map(it -> new FlightDto(
                                    it.getCompany(),
                                    it.getFlightNumber(),
                                    new MonetaryDto(it.getPrice().getAmount(), it.getPrice().getCurrency()),
                                    it.getDate(),
                                    it.getTime())
                            ).collect(Collectors.toList());

                    return new BookingAdditionalDto(holderDto, flightDtoList, booking.isConfirmed());
                }).orElseThrow(() ->
                        new ObjectNotFoundException(messages.getMessage("error.booking.not_found", id))
                );
    }

    @Override
    public Booking findBooking(Long id) {
        return bookingRepository.findById(id)
               .orElseThrow(() ->
                        new ObjectNotFoundException(messages.getMessage("error.booking.not_found", id))
                );
    }

    @Override
    public void deleteById(Long id) {
        Booking booking = findBooking(id);
        bookingRepository.deleteById(booking);
    }

    @Override
    public void confirm(Long id) {
        Booking booking = findBooking(id);
        booking.setConfirmed(true);
    }
}
