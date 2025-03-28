package com.greennplanne.aeroplane.reservation;

import com.greennplanne.aeroplane.flight.Flight;
import com.greennplanne.aeroplane.flight.FlightRepository;
import com.greennplanne.aeroplane.user.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationMapper {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;


    public ReservationMapper(ReservationRepository reservationRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }


    public Reservation toReservation(User user, Flight flight, String seatNumber, String reservationNumber) {

        return Reservation.builder()
                .flight(flight)
                .user(user)
                .reservationNumber(reservationNumber)
                .seatNumber(seatNumber)
                .build();
    }

    public ReservationResponse toReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .flight(reservation.getFlight())
                .userEmail(reservation.getUser().getEmail())
                .reservationNumber(reservation.getReservationNumber())
                .seatNumber(reservation.getSeatNumber())
                .status(reservation.getStatus())
                .build();

    }
}