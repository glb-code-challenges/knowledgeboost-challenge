package com.globant.knowledgeboostchallenge.controllers.impl;

import com.globant.knowledgeboostchallenge.controllers.WeatherController;
import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import com.globant.knowledgeboostchallenge.services.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class WeatherControllerImpl implements WeatherController {

    private  WeatherService weatherService;

    public WeatherControllerImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public ResponseEntity<WeatherInfoResponse> getWeatherByCityAndAppId(String cityName, String appId) {
        return  weatherService.getWeatherByCityAndAppId(cityName,appId);
    }

   @Override
   public ResponseEntity<WeatherInfoResponse> getWeatherByLatAndLonAndAppId(String latitude, String longitude, String appId) {
       return weatherService.getWeatherByLatAndLonAndAppId(latitude,longitude,appId);
   }


}
