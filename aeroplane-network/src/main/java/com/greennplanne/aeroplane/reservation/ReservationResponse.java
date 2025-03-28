package com.greennplanne.aeroplane.reservation;

import com.greennplanne.aeroplane.flight.Flight;
import com.greennplanne.aeroplane.user.User;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {

    private Integer id;
    private Flight flight;
    private String userEmail;
    private String reservationNumber;
    private String seatNumber;
    private ReservationStatus status;

}