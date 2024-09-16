package com.lopezkristian.codechallenge.services;

import com.lopezkristian.codechallenge.model.LogWeather;

public interface IWeatherService {

    LogWeather getCity(String cityName);

    LogWeather getWeatherLatitudeLongitude(String latitude, String longitude);

    void saveTransaction(LogWeather logWeather);

    void errorTransaction(LogWeather logWeather);

}
