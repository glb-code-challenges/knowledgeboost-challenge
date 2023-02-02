package com.globant.knowledgeboostchallenge.services;

import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import org.springframework.http.ResponseEntity;

public interface WeatherService {
    ResponseEntity<WeatherInfoResponse> getWeatherByLatAndLonAndAppId(String latitude, String longitude, String appId);

    ResponseEntity<WeatherInfoResponse> getWeatherByCityAndAppId(String cityName,String appId);

    WeatherInfoResponse saveWeatherInfoResponse(WeatherInfoResponse weatherInfoResponse);
}
