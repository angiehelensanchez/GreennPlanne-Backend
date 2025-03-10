package com.greennplanne.aeroplane.route;

import com.greennplanne.aeroplane.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Tag(name="Route")
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/routes")
    public ResponseEntity<Route> saveRoute(
            @Valid @RequestBody RouteRequest request
    ){
        return ResponseEntity.ok(routeService.save(request));
    }

    @GetMapping("/routes/{route-id}")
    public ResponseEntity<RouteResponse> getRouteById(
            @PathVariable("route-id") Integer routeId
    ){
        return ResponseEntity.ok(routeService.findById(routeId));
    }

    @GetMapping("/routes")
    public ResponseEntity<PageResponse<RouteResponse>> getAllRoutes(
            @RequestParam(name="page" , defaultValue = "0", required = false)int page,
            @RequestParam(name="size", defaultValue = "10", required = false)int size
    ){
        return ResponseEntity.ok(routeService.findAllRoutes(page, size));
    }

    @DeleteMapping("/routes/{route-id}")
    public ResponseEntity<RouteResponse> deleteRouteById(
            @PathVariable("route-id") Integer routeId
    ){
        return ResponseEntity.ok(routeService.deleteById(routeId));
    }
}
