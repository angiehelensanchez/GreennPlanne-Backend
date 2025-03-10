package com.greennplanne.aeroplane.route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteResponse {
    private String departure;
    private String arrival;
    private String consume;
}
