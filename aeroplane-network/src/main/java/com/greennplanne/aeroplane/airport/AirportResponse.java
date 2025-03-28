package com.greennplanne.aeroplane.airport;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportResponse {
    private Integer id;
    private String name;
    private String city;
    private String country;
    private String code;
}
