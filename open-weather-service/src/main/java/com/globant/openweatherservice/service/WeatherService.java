package com.globant.openweatherservice.service;

import com.globant.openweatherservice.dto.WeatherResponseDto;

public interface WeatherService {
    /**
     * This method is intended to return the weather information of a
     * specified city
     * @param city The city that you want the weather
     * @return Details of the weather of the specified city.
     */
    WeatherResponseDto cityWeatherRequest (String city);

    /**
     * This method is intended to return the weather information of a
     * determined location by its latitude and longitude
     * @param latitude The latitude of the location you are intended to get the weather
     * @param longitude The longitude of the location you are intended to get the weather
     * @return Details of the weather of the specified location by its latitude and longitude
     */
    WeatherResponseDto latitudeLongitudeWeatherRequest (Float latitude, Float longitude);
}
