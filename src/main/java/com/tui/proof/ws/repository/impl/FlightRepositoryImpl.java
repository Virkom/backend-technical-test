package com.tui.proof.ws.repository.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.FlightRepository;

@Service
public class FlightRepositoryImpl implements FlightRepository {

    private final List<Flight> flightList = new ArrayList<>();

    @PostConstruct
    private void setUp() {
        IntStream.range(1, 10)
                .forEach(it -> {
                    Flight flight = new Flight(
                            "company #" + it,
                            "flNum #" + it,
                            new Monetary(new BigDecimal(it * 50), "USD"),
                            LocalDate.of(2020, 12, it),
                            LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                    );
                    flightList.add(flight);
                });
    }

    @Override
    public List<Flight> findAvailableFlights() {
        return flightList;
    }

    @Override
    public boolean isAvailable(Flight flight) {
        return flightList.contains(flight);
    }

    @Override
    public void deleteFlight(Flight flight) {
        flightList.remove(flight);
    }
}
