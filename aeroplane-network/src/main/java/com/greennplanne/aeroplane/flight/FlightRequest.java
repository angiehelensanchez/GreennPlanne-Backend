package com.greennplanne.aeroplane.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FlightRequest(
        @NotNull(message = "Plane ID is mandatory")
        Integer planeId,
        @NotNull(message = "Departure ID is mandatory")
        Integer departureAirportId,
        @NotNull(message = "Arrival ID is mandatory")
        Integer arrivalAirportId,
        @NotNull(message = "Date is mandatory")
        LocalDateTime departureTime,
        @NotNull(message = "Available seats is mandatory")
        Integer availableSeats,
        @NotNull(message = "Status is mandatory")
        @NotEmpty(message = "Status is mandatory")
        @NotBlank(message = "Status is mandatory")
        String status
){

}
