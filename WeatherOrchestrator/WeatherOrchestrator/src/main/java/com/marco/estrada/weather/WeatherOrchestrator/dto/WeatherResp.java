package com.marco.estrada.weather.WeatherOrchestrator.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Data
public class WeatherResp {


    @JsonProperty("coord")
    Position position;

    @JsonProperty("weather")
    List<Weather> weatherList;

    String base;

    Main main;

    String visibility;

    Wind wind;

    @JsonProperty("clouds")
    Cloud cloud;

    long dt;

    Sys sys;

    long timezone;

    long id;

    String name;

    long cod;
}
