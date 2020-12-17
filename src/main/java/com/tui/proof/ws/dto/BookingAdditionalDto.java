package com.tui.proof.ws.dto;

import lombok.Value;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class BookingAdditionalDto {

    @NotNull
    HolderDto holder;

    @NotEmpty
    List<FlightDto> flights;
    
    boolean confirmed;
}
