package com.greennplanne.aeroplane.route;

import com.greennplanne.aeroplane.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route extends BaseEntity {

    private String departure;
    private String arrival;
    private String consume;
}
