package com.greennplanne.aeroplane.route;

import org.springframework.stereotype.Service;

@Service
public class RouteMapper {

    public Route toRoute(RouteRequest routeRequest) {
        return Route.builder()
                .departure(routeRequest.departure())
                .arrival(routeRequest.arrival())
                .consume(routeRequest.consume())
                .build();
    }
    public RouteResponse toRouteResponse(Route route) {
        return RouteResponse.builder()
                .departure(route.getDeparture())
                .arrival(route.getArrival())
                .consume(route.getConsume())
                .build();
    }
}
