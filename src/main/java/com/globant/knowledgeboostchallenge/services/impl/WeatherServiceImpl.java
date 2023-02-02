package com.globant.knowledgeboostchallenge.services.impl;

import com.globant.knowledgeboostchallenge.utils.WeatherUtil;
import com.globant.knowledgeboostchallenge.client.OpenWeatherClient;

import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import com.globant.knowledgeboostchallenge.repositories.WeatherRepository;
import com.globant.knowledgeboostchallenge.services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class WeatherServiceImpl implements WeatherService {

    private final OpenWeatherClient openWeatherClient;
    private final WeatherRepository repository;

    public WeatherServiceImpl(OpenWeatherClient openWeatherClient, WeatherRepository repository) {
        this.openWeatherClient = openWeatherClient;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<WeatherInfoResponse> getWeatherByLatAndLonAndAppId(String latitude, String longitude, String appId) {
         var infoDtoResponseEntity=openWeatherClient.getWeatherByLatAndLonAndAppId(latitude,longitude,appId);
         return new ResponseEntity(saveWeatherInfoResponse(WeatherUtil.getWeatherInfoResponse(infoDtoResponseEntity)), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<WeatherInfoResponse> getWeatherByCityAndAppId(String cityName, String appId) {
        var infoDtoResponseEntity=openWeatherClient.getWeatherByCityAndAppId(cityName,appId);
        return new ResponseEntity(saveWeatherInfoResponse(WeatherUtil.getWeatherInfoResponse(infoDtoResponseEntity)), HttpStatus.OK) ;
    }

    @Override
    public WeatherInfoResponse saveWeatherInfoResponse(WeatherInfoResponse weatherInfoResponse){
       return repository.save(weatherInfoResponse);
    }

}
