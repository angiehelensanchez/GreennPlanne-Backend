package com.greennplanne.aeroplane.plane;

import com.greennplanne.aeroplane.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Tag(name = "Plane")
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping("/planes")
    public ResponseEntity<Plane> savePlane(
            @Valid @RequestBody PlaneRequest request
    ){
        return ResponseEntity.ok(planeService.save(request));
    }

    @GetMapping("/planes/{plane-id}")
    public ResponseEntity<PlaneResponse> getPlaneById(
            @PathVariable("plane-id") Integer planeId
    ) {
        return ResponseEntity.ok(planeService.findById(planeId));
    }

    @GetMapping("/planes")
    public ResponseEntity<PageResponse<PlaneResponse>> getAllPlanes(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(planeService.findAllPLanes(page, size));
    }
}
