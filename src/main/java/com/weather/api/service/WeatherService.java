package com.weather.api.service;

import com.weather.api.client.response.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherByCityName(String cityName);
    WeatherResponse getWeatherByLatitudeAndLongitude(Double latitude, Double longitude);
}
