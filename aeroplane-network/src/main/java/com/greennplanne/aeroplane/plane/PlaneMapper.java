package com.greennplanne.aeroplane.plane;

import org.springframework.stereotype.Service;

@Service
public class PlaneMapper {

    public Plane toPlane(PlaneRequest request) {
        return Plane.builder()
                .name(request.name())
                .capacity(request.capacity())
                .build();
    }
    public PlaneResponse toPlaneResponse(Plane plane) {
        return PlaneResponse.builder()
                .name(plane.getName())
                .capacity(plane.getCapacity())
                .build();
    }
}
