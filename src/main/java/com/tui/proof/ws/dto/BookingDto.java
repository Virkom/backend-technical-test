package com.tui.proof.ws.dto;

import lombok.Value;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tui.proof.ws.validation.Available;

@Value
public class BookingDto {

    @NotNull
    HolderDto holder;

    @NotEmpty
    List<@Available FlightDto> flights;
}
