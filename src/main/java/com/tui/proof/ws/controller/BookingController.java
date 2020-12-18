package com.tui.proof.ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.ws.dto.BookingAdditionalDto;
import com.tui.proof.ws.dto.BookingDto;
import com.tui.proof.ws.event.AddBookingEvent;
import com.tui.proof.ws.event.ConfirmBookingEvent;
import com.tui.proof.ws.event.DeleteBookingEvent;
import com.tui.proof.ws.event.dispatcher.EventDispatcher;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.service.BookingService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final EventDispatcher eventDispatcher;

    @Operation(summary = "Create a new booking",
            requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class)
                            )
                    }),
            responses = {
                    @ApiResponse(responseCode = "201", description = "New booking added",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Booking.class))
                            }),
                    @ApiResponse(responseCode = "400", description = "Booking request is invalid", content = @Content),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PostMapping("/")
    public ResponseEntity<Booking> addBooking(@Valid @RequestBody BookingDto request) {
        return new ResponseEntity<>(bookingService.addBooking(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get booking details",
            parameters = {
                    @Parameter(name = "id", description = "Booking id")
            },
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Booking.class))
                            }),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookingAdditionalDto> findBooking(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookingService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete a booking",
            parameters = {
                    @Parameter(name = "id", description = "Booking id")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking was deleted"),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") Long id) {
        eventDispatcher.dispatch(new DeleteBookingEvent(id));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Confirm a booking",
            parameters = {
                    @Parameter(name = "id", description = "Booking id")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking was confirmed"),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @GetMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmBooking(@PathVariable("id") Long id) {
        eventDispatcher.dispatch(new ConfirmBookingEvent(id));
        return ResponseEntity.ok().build();
    }


}
