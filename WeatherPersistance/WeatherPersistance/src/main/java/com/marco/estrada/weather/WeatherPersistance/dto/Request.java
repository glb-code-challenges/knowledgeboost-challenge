package com.marco.estrada.weather.WeatherPersistance.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;


@Jacksonized
@Builder
@Data
public class Request {

    int statusCode;

    Date date;

    String cityName;

    ErrorDetail errorDetail;

}
