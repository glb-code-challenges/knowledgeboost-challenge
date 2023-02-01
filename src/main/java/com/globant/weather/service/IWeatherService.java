package com.globant.weather.service;

import com.globant.weather.dto.WeatherResponse;
import com.globant.weather.exeption.WeatherException;

public interface IWeatherService {

    public WeatherResponse getByCityName(String cityName) throws WeatherException;
    public WeatherResponse getByCoordinates(String latitude, String longitude) throws WeatherException;
}
