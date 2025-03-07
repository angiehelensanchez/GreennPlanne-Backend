package com.greennplanne.aeroplane.plane;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaneResponse {

    private String name;
    private Integer capacity;

}
