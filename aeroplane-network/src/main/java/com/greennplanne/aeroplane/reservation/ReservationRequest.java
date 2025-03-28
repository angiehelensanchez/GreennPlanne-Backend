package com.greennplanne.aeroplane.reservation;

import com.greennplanne.aeroplane.flight.Flight;
import com.greennplanne.aeroplane.user.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationRequest(
        @NotNull(message = "Flight ID is mandatory")
        Integer flightId,
        Integer userId
) {
}
