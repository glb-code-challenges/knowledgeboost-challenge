package com.aforo255.appdeposit.service;

import com.aforo255.appdeposit.model.WeatherModel;

public interface IWeatherService {
    public WeatherModel getByLatLong(String lat, String lon);
    public WeatherModel getByCityName(String cityName);
}
