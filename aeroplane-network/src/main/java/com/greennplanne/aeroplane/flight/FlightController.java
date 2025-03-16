package com.greennplanne.aeroplane.flight;

import com.greennplanne.aeroplane.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Tag(name="Flight")
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/flight")
    public ResponseEntity<Flight> saveFlight(
            @Valid @RequestBody FlightRequest request
    ){
        return ResponseEntity.ok(flightService.save(request));
    }

    @GetMapping("/flights/{flight-id}")
    public ResponseEntity<FlightResponse> getFlightById(
            @PathVariable("flight-id") Integer flightId
    ){
        return ResponseEntity.ok(flightService.findById(flightId));
    }

    @GetMapping("/flights")
    public ResponseEntity<PageResponse<FlightResponse>> getAllFlights(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(flightService.findAllPlanes(page, size));
    }

    @DeleteMapping("/flights/{flight-id}")
    public ResponseEntity<FlightResponse> deleteFlightById(
            @PathVariable("flight-id") Integer flightId
    ){
        return ResponseEntity.ok(flightService.deleteById(flightId));
    }
}
