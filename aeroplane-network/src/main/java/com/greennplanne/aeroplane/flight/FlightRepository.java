package com.greennplanne.aeroplane.flight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("""
            SELECT flight
            from Flight flight
            WHERE flight.id = :flightId
                        """)
    Page<Flight> findByFlightId(Integer flightId, Pageable pageable);

    @Modifying
    @Query("UPDATE Flight f SET f.availableSeats = f.availableSeats - 1 WHERE f.id = :flightId AND f.availableSeats > 0")
    int reduceAvailableSeats(@Param("flightId") int flightId);

    @Modifying
    @Query("UPDATE Flight f SET f.availableSeats = f.availableSeats + 1 WHERE f.id = :flightId")
    int increaseAvailableSeats(@Param("flightId") int flightId);
}
