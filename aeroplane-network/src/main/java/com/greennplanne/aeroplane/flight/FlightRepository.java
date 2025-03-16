package com.greennplanne.aeroplane.flight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("""
            SELECT flight
            from Flight flight
            WHERE flight.id = :flightId
                        """)
    Page<Flight> findByFlightId(Integer flightId, Pageable pageable);

}
