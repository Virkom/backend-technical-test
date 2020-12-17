package com.tui.proof.ws.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Booking {

    Long id;
    Holder holder;
    List<Flight> flights;
    boolean confirmed;

    public Booking(Holder holder, List<Flight> flights) {
        this.holder = holder;
        this.flights = flights;
    }
}
