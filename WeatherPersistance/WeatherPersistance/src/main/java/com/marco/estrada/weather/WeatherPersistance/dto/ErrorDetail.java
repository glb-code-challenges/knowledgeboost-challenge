package com.marco.estrada.weather.WeatherPersistance.dto;


import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Value
public class ErrorDetail {

    String description;

}
