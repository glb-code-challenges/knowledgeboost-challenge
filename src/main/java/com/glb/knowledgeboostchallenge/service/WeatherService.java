package com.glb.knowledgeboostchallenge.service;

import com.glb.knowledgeboostchallenge.dto.WeatherRequest;
import com.glb.knowledgeboostchallenge.dto.WeatherResponse;
import com.glb.knowledgeboostchallenge.exception.BaseWeatherException;

public interface WeatherService {

    WeatherResponse getWeatherByCity(WeatherRequest request) throws BaseWeatherException;

    WeatherResponse getWeatherByCoordinates(WeatherRequest request)
        throws BaseWeatherException;

}
