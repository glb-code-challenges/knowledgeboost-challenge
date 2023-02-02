package com.marco.estrada.weather.WeatherOrchestrator.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Sys {

    int type;

    long id;

    String country;

    long sunrise;

    long sunset;
}
