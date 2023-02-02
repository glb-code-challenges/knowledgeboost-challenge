package com.globant.dnlpalestina.WeatherService.services;

import com.globant.dnlpalestina.WeatherService.models.entities.WeatherDetails;
import com.globant.dnlpalestina.WeatherService.models.entities.WeatherEntity;
import net.minidev.json.JSONObject;

public interface WeatherService {

    Iterable<WeatherEntity> findAll();

    WeatherDetails getWeatherDetailsByCityName(String cityName);

    WeatherDetails getWeatherDetailsByLatLong(String lat, String lon);

}
