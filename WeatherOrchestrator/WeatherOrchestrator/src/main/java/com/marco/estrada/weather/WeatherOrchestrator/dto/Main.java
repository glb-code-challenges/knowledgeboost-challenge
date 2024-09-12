package com.marco.estrada.weather.WeatherOrchestrator.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Main {

    double temp;

    double feels_like;

    double temp_min;

    double temp_max;

    long pressure;

    long humidity;

    long sea_level;

    long grnd_level;


}
