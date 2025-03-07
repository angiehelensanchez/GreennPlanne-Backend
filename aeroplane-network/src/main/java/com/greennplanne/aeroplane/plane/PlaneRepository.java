package com.greennplanne.aeroplane.plane;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  PlaneRepository extends JpaRepository<Plane, Integer> {

    @Query("""
            SELECT plane
            from Plane plane
            WHERE plane.id = :planeId
                        """)
    Page<Plane> findByPlaneId(Integer planeId, Pageable pageable);

}
