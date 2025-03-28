package com.greennplanne.aeroplane.reservation;

import com.greennplanne.aeroplane.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("""
            SELECT reservation
            from Reservation reservation
            WHERE reservation.user = :user
                        """)
    Page<Reservation> findByUser(User user, Pageable pageable);

}
