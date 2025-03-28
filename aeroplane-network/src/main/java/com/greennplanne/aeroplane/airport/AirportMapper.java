package com.greennplanne.aeroplane.airport;

import org.springframework.stereotype.Service;

@Service
public class AirportMapper {

    public Airport toAirport(AirportRequest airportRequest) {
        return Airport.builder()
                .name(airportRequest.name())
                .city(airportRequest.city())
                .country(airportRequest.country())
                .code(airportRequest.code())
                .build();
    }
    public AirportResponse toAirportResponse(Airport airport) {
        return AirportResponse.builder()
                .name(airport.getName())
                .city(airport.getCity())
                .country(airport.getCountry())
                .code(airport.getCode())
                .build();
    }

}
