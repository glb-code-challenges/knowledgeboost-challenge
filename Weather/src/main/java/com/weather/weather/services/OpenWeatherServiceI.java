package com.weather.weather.services;

import com.weather.weather.entities.RequestWeather;
import com.weather.weather.entities.ResponseWeather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public interface OpenWeatherServiceI {
    ResponseWeather consultWeather(RequestWeather requestWeather)throws HttpStatusCodeException;
}