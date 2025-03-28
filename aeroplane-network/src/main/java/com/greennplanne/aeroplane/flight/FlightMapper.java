package com.greennplanne.aeroplane.flight;


import com.greennplanne.aeroplane.airport.Airport;
import com.greennplanne.aeroplane.airport.AirportRepository;
import com.greennplanne.aeroplane.plane.Plane;
import com.greennplanne.aeroplane.plane.PlaneRepository;
import org.springframework.stereotype.Service;

@Service

public class FlightMapper {

    private final PlaneRepository planeRepository;

    private final AirportRepository airportRepository;

    public FlightMapper(PlaneRepository planeRepository, AirportRepository airportRepository) {
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
    }
    public Flight toFlight(FlightRequest flightRequest, Airport origin, Airport destination) {
        Plane plane = planeRepository.findByPlaneId(flightRequest.planeId());

        return Flight.builder()
                .plane(plane)
                .arrivalAirport(origin)
                .departureAirport(destination)
                .departureTime(flightRequest.departureTime())
                .availableSeats(flightRequest.availableSeats())
                .status(FlightStatus.valueOf(flightRequest.status()))
                .build();
    }
    public FlightResponse toFlightResponse(Flight flight) {
        Plane plane = planeRepository.findByPlaneId(flight.getPlane().getId());;
        return FlightResponse.builder()
                .id(flight.getId())
                .plane(plane)
                .arrivalAirport(flight.getArrivalAirport())
                .departureAirport(flight.getDepartureAirport())
                .flightNumber(flight.getFlightNumber())
                .availableSeats(flight.getAvailableSeats())
                .status(String.valueOf(flight.getStatus()))
                .build();

    }
}
