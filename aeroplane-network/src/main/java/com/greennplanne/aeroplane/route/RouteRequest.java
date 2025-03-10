package com.greennplanne.aeroplane.route;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RouteRequest(
        @NotNull(message = "Departure is mandatory")
        @NotEmpty(message = "Departure is mandatory")
        @NotBlank(message = "Departure is mandatory")
        String departure,
        @NotNull(message = "Arrival is mandatory")
        @NotEmpty(message = "Arrival is mandatory")
        @NotBlank(message = "Arrival is mandatory")
        String arrival,
        @NotNull(message = "Consume is mandatory")
        @NotEmpty(message = "Consume is mandatory")
        @NotBlank(message = "Consume is mandatory")
        String consume
){

}
