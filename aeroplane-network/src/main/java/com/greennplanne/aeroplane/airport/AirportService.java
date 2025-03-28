package com.greennplanne.aeroplane.airport;

import com.greennplanne.aeroplane.common.PageResponse;
import com.greennplanne.aeroplane.flight.Flight;
import com.greennplanne.aeroplane.route.RouteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public Airport save(AirportRequest airportRequest) {
        Airport airport = airportMapper.toAirport(airportRequest);
        return airportRepository.save(airport);
    }

    public AirportResponse findById(Integer id) {
        return airportRepository.findById(id)
                .map(airportMapper::toAirportResponse)
                .orElseThrow(()-> new EntityNotFoundException("Airport with id " + id + " not found"));
    }

    public PageResponse<AirportResponse> findAllAirports(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Airport> airports = airportRepository.findAll(pageable);
        List<AirportResponse> airportResponse = airports.stream()
                .map(airportMapper::toAirportResponse)
                .toList();
        return new PageResponse<>(
                airportResponse,
                airports.getNumber(),
                airports.getSize(),
                airports.getTotalElements(),
                airports.getTotalPages(),
                airports.isFirst(),
                airports.isLast()
        );
    }

    public AirportResponse deleteById(Integer id) {
        AirportResponse airportResponse = findById(id);
        if(airportResponse != null) {
            airportRepository.deleteById(id);
        }
        return airportResponse;
    }

    public AirportResponse update(Integer id, AirportRequest airportRequest) {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport with id " + id + " not found"));
        Optional<Airport> conflictingAirport = airportRepository.findByCode(airportRequest.code());
        if(conflictingAirport.isPresent() && !conflictingAirport.get().getId().equals(existingAirport.getId())) {
            throw new RuntimeException("An airport with code " + existingAirport.getCode() + " already exists");
        }
        existingAirport.setCode(airportRequest.code());
        existingAirport.setName(airportRequest.name());
        existingAirport.setCity(airportRequest.city());
        existingAirport.setCountry(airportRequest.country());
        Airport updatedAirport = airportRepository.save(existingAirport);
        return airportMapper.toAirportResponse(updatedAirport);
    }


}
