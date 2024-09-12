package com.marco.estrada.weather.WeatherOrchestrator.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Date;

@Jacksonized
@Builder
@Data
public class Request implements Serializable {

    int statusCode;

    Date date;

    String cityName;

    ErrorDetail errorDetail;

}