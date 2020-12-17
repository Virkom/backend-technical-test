package com.tui.proof.ws.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.event.AddFlightToBookingEvent;
import com.tui.proof.ws.event.DeleteFlightFromBookingEvent;
import com.tui.proof.ws.event.dispatcher.EventDispatcher;
import com.tui.proof.ws.validation.Available;

@Slf4j
@RestController
@AllArgsConstructor
@OpenAPIDefinition
@RequestMapping("/api/booking")
public class FlightController {

    private final EventDispatcher eventDispatcher;

    @Operation(summary = "Add a flight to a booking",
            parameters = {
                    @Parameter(name = "bookingId", description = "Booking id")
            },
            requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FlightDto.class))
                    }),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flight was added"),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PutMapping("/{bookingId}/flight")
    public ResponseEntity<Void> addFlight(@PathVariable("bookingId") Long id,
                                          @Valid @Available @RequestBody FlightDto flightDto) {
        eventDispatcher.dispatch(new AddFlightToBookingEvent(id, flightDto));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a flight from a booking",
            parameters = {
                    @Parameter(name = "bookingId", description = "Booking id")
            },
            requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FlightDto.class))}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flight was deleted from a booking"),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @DeleteMapping("/{bookingId}/flight")
    public ResponseEntity<Void> deleteFlight(@PathVariable("bookingId") Long id,
                                             @Valid @Available @RequestBody FlightDto flightDto) {
        eventDispatcher.dispatch(new DeleteFlightFromBookingEvent(id, flightDto));
        return ResponseEntity.ok().build();
    }
}
