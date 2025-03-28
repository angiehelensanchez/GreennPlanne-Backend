package com.greennplanne.aeroplane.reservation;

import com.greennplanne.aeroplane.common.PageResponse;
import com.greennplanne.aeroplane.flight.Flight;
import com.greennplanne.aeroplane.flight.FlightRepository;
import com.greennplanne.aeroplane.user.User;
import com.greennplanne.aeroplane.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final ReservationMapper reservationMapper;

    public Reservation createReservation(ReservationRequest reservationRequest) {

        int updated = flightRepository.reduceAvailableSeats(reservationRequest.flightId());

        if (updated == 0) {
            throw new RuntimeException("No seats available to this flight");
        }

        User user = userRepository.findById(reservationRequest.userId())
                .orElseThrow(() -> new RuntimeException("User with id " + reservationRequest.userId() + " not found"));
        Flight flight = flightRepository.findById(reservationRequest.flightId())
                .orElseThrow(() -> new RuntimeException("Flight with id " + reservationRequest.flightId() + " not found"));

        if(LocalDateTime.now().isAfter(flight.getDepartureTime())){
            throw new RuntimeException("Reservations can only be realized befored the flight departure time");
        }

        String seatNumber = generateSeatNumber(flight);
        String reservationNumber = generateReservationNumber(flight);
        Reservation reservation = reservationMapper.toReservation(user, flight, seatNumber, reservationNumber);
        return reservationRepository.save(reservation);
    }


    public String generateReservationNumber(Flight flight){
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        String departure = flight.getDepartureAirport().getCode();
        String arrival = flight.getArrivalAirport().getCode();

        return departure + number + arrival;

    }

    public String generateSeatNumber(Flight flight) {
        Integer number = flight.getAvailableSeats();
        String seatNumber;
        if(number%2==0){
            seatNumber = "A"+number;
        }else {
            seatNumber = "B"+number;
        }
        return seatNumber;
    }

    public void cancelReservation(Integer reservationId, Integer userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with id " + reservationId + " not found"));

        User user = userRepository.findById(reservation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User with id " + reservation.getUser().getId() + " not found"));

        if (!reservation.getUser().equals(user)) {
            throw new IllegalArgumentException("You don´t have permission to cancel this reservation");
        }
        reservationRepository.delete(reservation);
        flightRepository.increaseAvailableSeats(reservation.getFlight().getId());
    }

    public PageResponse<ReservationResponse> findReservationsByUser(Integer userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservations = reservationRepository.findByUser(user, pageable);

        List<ReservationResponse> reservationResponse = reservations.stream()
                .map(reservationMapper::toReservationResponse)
                .toList();
        return new PageResponse<>(
                reservationResponse,
                reservations.getNumber(),
                reservations.getSize(),
                reservations.getTotalElements(),
                reservations.getTotalPages(),
                reservations.isFirst(),
                reservations.isLast()
        );
    }
}