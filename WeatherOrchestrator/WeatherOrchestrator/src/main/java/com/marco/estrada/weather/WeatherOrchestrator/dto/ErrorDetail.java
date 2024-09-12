package com.marco.estrada.weather.WeatherOrchestrator.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Builder
@Jacksonized
@Value
public class ErrorDetail implements Serializable {

    String description;

}
