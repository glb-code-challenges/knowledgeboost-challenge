package com.marco.estrada.weather.WeatherOrchestrator.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Weather {

    long id;

    String main;

    String description;

    String icon;
}
