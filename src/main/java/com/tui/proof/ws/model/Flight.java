package com.tui.proof.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private String company;
    private String flightNumber;
    private Monetary price;
    private LocalDate date;
    private LocalTime time;
}
