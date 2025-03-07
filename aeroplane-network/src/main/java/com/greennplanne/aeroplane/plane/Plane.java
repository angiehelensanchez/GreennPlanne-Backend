package com.greennplanne.aeroplane.plane;

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
public class Plane extends BaseEntity {

    private String name;
    private Integer capacity;

}
