package com.tui.proof.ws.dto;

import lombok.Value;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

@Value
public class FlightDto {

    String company;
    String flightNumber;
    MonetaryDto price;

    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate date;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    LocalTime time;

//    public FlightDto(Flight flight) {
//        this.company = flight.getCompany();
//        this.flightNumber = flight.getFlightNumber();
//        this.price = new MonetaryDto(flight.getPrice());
//        this.date = flight.getDate();
//        this.time = flight.getTime();
//    }
}
