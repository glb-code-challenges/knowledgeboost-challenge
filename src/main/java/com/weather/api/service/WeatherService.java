package com.weather.api.service;

import com.weather.api.client.response.WeatherResponse;
import com.weather.api.entity.ExecutionEntity;
import com.weather.api.record.WeatherAndDBDataRecord;

public interface WeatherService {
    WeatherAndDBDataRecord<WeatherResponse, ExecutionEntity> getWeatherByCityName(String cityName);
    WeatherAndDBDataRecord<WeatherResponse, ExecutionEntity> getWeatherByLatitudeAndLongitude(Double latitude, Double longitude);
}
