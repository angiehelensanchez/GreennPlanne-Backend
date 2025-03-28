package com.greennplanne.aeroplane.airport;

public record AirportRequest(
        Integer id,
        String name,
        String city,
        String country,
        String code
) {
}
