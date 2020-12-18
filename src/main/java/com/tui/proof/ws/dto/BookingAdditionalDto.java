package com.tui.proof.ws.dto;

import lombok.Value;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;

@Value
public class BookingAdditionalDto {

    @NotNull
    HolderDto holder;

    @NotEmpty
    List<FlightDto> flights;
    
    boolean confirmed;
}
