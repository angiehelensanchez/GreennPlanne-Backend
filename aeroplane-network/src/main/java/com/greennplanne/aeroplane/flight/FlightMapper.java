package com.greennplanne.aeroplane.flight;


import com.greennplanne.aeroplane.plane.Plane;
import com.greennplanne.aeroplane.plane.PlaneRepository;
import com.greennplanne.aeroplane.route.Route;
import com.greennplanne.aeroplane.route.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {

    private final PlaneRepository planeRepository;
    private final RouteRepository routeRepository;

    public FlightMapper(PlaneRepository planeRepository, RouteRepository routeRepository) {
        this.planeRepository = planeRepository;
        this.routeRepository = routeRepository;
    }
    public Flight toFlight(FlightRequest flightRequest) {
        Plane plane = planeRepository.findByPlaneId(flightRequest.planeId());

        Route route = routeRepository.findByRouteId(flightRequest.routeId());

        return Flight.builder()
                .plane(plane)
                .route(route)
                .departureTime(flightRequest.departureTime())
                .availableSeats(flightRequest.availableSeats())
                .status(flightRequest.status())
                .build();
    }
    public FlightResponse toFlightResponse(Flight flight) {
        Plane plane = planeRepository.findByPlaneId(flight.getPlane().getId());
        Route route = routeRepository.findByRouteId(flight.getRoute().getId());

        return FlightResponse.builder()
                .id(flight.getId())
                .plane(plane)
                .route(route)
                .flightNumber(flight.getFlightNumber())
                .availableSeats(flight.getAvailableSeats())
                .status(flight.getStatus())
                .build();

    }
}
