package com.marco.estrada.weather.WeatherOrchestrator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Builder
@Jacksonized
@Data
public class Position {

    @JsonProperty("lat")
    double latitude;

    @JsonProperty("lon")
    double longitude;
}
