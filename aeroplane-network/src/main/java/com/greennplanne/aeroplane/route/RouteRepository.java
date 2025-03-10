package com.greennplanne.aeroplane.route;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("""
            SELECT route
            from Route route
            where route.id =:routeId
            """)
    Page<Route> findByRouteId(Integer routeId, Pageable pageable);
}
