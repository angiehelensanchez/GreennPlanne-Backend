package com.greennplanne.aeroplane.route;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("""
            SELECT route
            from Route route
            where route.id =:routeId
            """)
    Page<Route> findByRouteIdPageable(Integer routeId, Pageable pageable);
    @Query("""
            SELECT route
            from Route route
            where route.id =:routeId
            """)
    Route findByRouteId(Integer routeId);
}
