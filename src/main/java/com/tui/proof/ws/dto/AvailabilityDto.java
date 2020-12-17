package com.tui.proof.ws.dto;

import lombok.Value;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Value
public class AvailabilityDto {

    @NotEmpty
    String airportOrigin;

    @NotEmpty
    String airportDestination;

    @NotNull
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate dateFrom;

    @NotNull
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate dateTo;

    @NotNull
    PaxesDto paxes;
}
