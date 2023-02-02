package com.weather.weather.services;

import com.weather.weather.entities.RequestWeather;
import com.weather.weather.entities.ResponseWeather;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

@Service
public interface WeatherServiceI {
    RequestWeather createRequestWeather(String cityName);
    RequestWeather createRequestWeather(String latitude,String longitude);
    Object consultWeather(RequestWeather requestWeather);
}