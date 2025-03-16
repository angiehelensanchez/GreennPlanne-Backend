package com.greennplanne.aeroplane.flight;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greennplanne.aeroplane.common.BaseEntity;
import com.greennplanne.aeroplane.plane.Plane;
import com.greennplanne.aeroplane.route.Route;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "plane_id")
    @JsonBackReference
    private Plane plane;
    @ManyToOne
    @JoinColumn(name="route_id")
    @JsonBackReference
    private Route route;
    private String flightNumber;
    private LocalDateTime departureTime;
    private Integer availableSeats;
    private String status;


}
