package com.greennplanne.aeroplane.plane;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PlaneRequest(
        @NotNull(message = "Name is mandatory")
        @NotEmpty(message = "Name is mandatory")
        @NotBlank(message = "Name is mandatory")
        String name,
        @Positive(message = "Capacity is mandatory")
        @NotNull(message = "Capacity is mandatory")
        Integer capacity
) {
}
