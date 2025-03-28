package com.greennplanne.aeroplane.reservation;

import com.greennplanne.aeroplane.flight.Flight;
import com.greennplanne.aeroplane.flight.FlightRepository;
import com.greennplanne.aeroplane.flight.FlightRequest;
import com.greennplanne.aeroplane.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping("/public/reservations")
    public Reservation createReservation(
            @Valid @RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }



}