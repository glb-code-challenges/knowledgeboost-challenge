package com.globant.knowledgeboostchallenge.client;

import com.globant.knowledgeboostchallenge.dao.dtos.WeatherInfoDto;
import org.springframework.http.ResponseEntity;

public interface OpenWeatherClient {

    ResponseEntity<WeatherInfoDto> getWeatherByLatAndLonAndAppId(String lat, String lon, String appId);
    ResponseEntity<WeatherInfoDto> getWeatherByCityAndAppId(String city, String appId);

}
