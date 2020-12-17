package com.tui.proof.ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.ws.dto.AvailabilityDto;
import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.service.AvailabilityService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @Operation(summary = "Get available flights",
            requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AvailabilityDto.class))}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found flights",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FlightDto.class)))
                            }),
                    @ApiResponse(responseCode = "400", description = "Availability request is invalid", content = @Content),
                    @ApiResponse(responseCode = "401", description = "User unauthorized", content = @Content)
            },
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PostMapping("/")
    public ResponseEntity<List<FlightDto>> availability(@Valid @RequestBody AvailabilityDto availabilityDto) {
        return new ResponseEntity<>(availabilityService.availableFlights(availabilityDto), HttpStatus.OK);
    }
}
