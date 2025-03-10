package com.greennplanne.aeroplane.plane;

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
public class PlaneService{
    private final PlaneRepository planeRepository;
    private final PlaneMapper planeMapper;

    public Plane save(PlaneRequest planeRequest){
        Plane plane = planeMapper.toPlane(planeRequest);
        return planeRepository.save(plane);

    }

    public PlaneResponse findById(Integer id){
        return planeRepository.findById(id)
                .map(planeMapper::toPlaneResponse)
                .orElseThrow(() -> new EntityNotFoundException("Plane with id " + id + " not found"));
    }

    public PageResponse<PlaneResponse> findAllPlanes(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Plane> planes = planeRepository.findAll(pageable);
        List<PlaneResponse> planesResponse = planes.stream()
                .map(planeMapper::toPlaneResponse)
                .toList();
        return new PageResponse<>(
                planesResponse,
                planes.getNumber(),
                planes.getSize(),
                planes.getTotalElements(),
                planes.getTotalPages(),
                planes.isFirst(),
                planes.isLast()
        );
    }

}
