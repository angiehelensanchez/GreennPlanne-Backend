package com.greennplanne.aeroplane.route;

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
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    public Route save(RouteRequest routeRequest) {
        Route route = routeMapper.toRoute(routeRequest);
        return routeRepository.save(route);
    }

    public RouteResponse findById(Integer id) {
        return routeRepository.findById(id)
                .map(routeMapper::toRouteResponse)
                .orElseThrow(()-> new EntityNotFoundException("Route with id" + id + "not found"));

    }

    public PageResponse<RouteResponse> findAllRoutes(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Route> routes = routeRepository.findAll(pageable);
        List<RouteResponse> routeResponse = routes.stream()
                .map(routeMapper::toRouteResponse)
                .toList();
        return new PageResponse<>(
                routeResponse,
                routes.getNumber(),
                routes.getSize(),
                routes.getTotalElements(),
                routes.getTotalPages(),
                routes.isFirst(),
                routes.isLast()
        );
    }

    public RouteResponse deleteById(Integer id) {
        RouteResponse routeResponse = findById(id);
        if (routeResponse != null) {
            routeRepository.deleteById(id);
        }
        return routeResponse;
    }

}
