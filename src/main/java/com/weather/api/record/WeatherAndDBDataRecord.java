package com.weather.api.record;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WeatherAndDBDataRecord <T, U> (U databaseContent,
                                            T weather){

}
