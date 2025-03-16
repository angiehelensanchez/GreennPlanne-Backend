package com.greennplanne.aeroplane.flight;

import com.greennplanne.aeroplane.plane.Plane;
import com.greennplanne.aeroplane.plane.PlaneResponse;
import com.greennplanne.aeroplane.route.Route;
import com.greennplanne.aeroplane.route.RouteResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightResponse {

    private Integer id;
    private Plane plane;
    private Route route;
    private String flightNumber;
    private LocalDateTime departureTime;
    private Integer availableSeats;
    private String status;
}
