package com.tui.proof.ws.validation;

import lombok.AllArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.repository.FlightRepository;

@Component
@AllArgsConstructor
public class FlightAvailabilityValidator implements ConstraintValidator<Available, FlightDto> {

    private final FlightRepository flightRepository;

    public void initialize(Available constraint) {
    }

    public boolean isValid(FlightDto obj, ConstraintValidatorContext context) {
        Flight flight = new Flight();
        BeanUtils.copyProperties(obj, flight, "price");

        Monetary monetary = new Monetary();
        BeanUtils.copyProperties(obj.getPrice(), monetary);

        flight.setPrice(monetary);
        return flightRepository.isAvailable(flight);
    }
}
