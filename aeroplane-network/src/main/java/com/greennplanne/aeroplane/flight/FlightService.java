package com.greennplanne.aeroplane.flight;

import com.greennplanne.aeroplane.airport.Airport;
import com.greennplanne.aeroplane.airport.AirportRepository;
import com.greennplanne.aeroplane.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportRepository airportRepository;

    public Flight save(FlightRequest flightRequest) {
        Airport departureAirport = airportRepository.findById(flightRequest.departureAirportId())
                .orElseThrow(() -> new EntityNotFoundException("Airport with id " + flightRequest.departureAirportId() + " not found"));

        Airport arrivalAirport = airportRepository.findById(flightRequest.arrivalAirportId())
                .orElseThrow(() -> new EntityNotFoundException("Airport with id " + flightRequest.arrivalAirportId() + " not found"));

        Flight flight = flightMapper.toFlight(flightRequest, departureAirport, arrivalAirport);
        flight.setFlightNumber(generateFlightNumber(flight.getArrivalAirport().getCode(), flight.getDepartureAirport().getCode(),    flight.getDepartureTime().getDayOfYear(), flight.getDepartureTime().getYear()));
        return flightRepository.save(flight);
    }

    private String generateFlightNumber(String departure, String arrival, int dayOfYear, int year) {

        char departureChar = departure.charAt(0);
        char arrivalChar = arrival.charAt(0);

        return String.valueOf(departureChar + arrivalChar + dayOfYear + year);
    }

    public FlightResponse findById(Integer id) {
        return flightRepository.findById(id)
                .map(flightMapper::toFlightResponse)
                .orElseThrow(() -> new EntityNotFoundException("Flight with id " + id + " not found"));
    }


    public PageResponse<FlightResponse> findAllPlanes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Flight> flights = flightRepository.findAll(pageable);
        List<FlightResponse> flightResponse = flights.stream()
                .map(flightMapper::toFlightResponse)
                .toList();
        return new PageResponse<>(
                flightResponse,
                flights.getNumber(),
                flights.getSize(),
                flights.getTotalElements(),
                flights.getTotalPages(),
                flights.isFirst(),
                flights.isLast()
        );
    }

    public FlightResponse deleteById(Integer id) {
        FlightResponse flightResponse = findById(id);
        if (flightResponse != null) {
            flightRepository.deleteById(id);
        }
        return flightResponse;
    }


}
