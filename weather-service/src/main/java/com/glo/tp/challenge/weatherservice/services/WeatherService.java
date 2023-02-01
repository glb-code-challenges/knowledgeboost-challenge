package com.glo.tp.challenge.weatherservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.glo.tp.challenge.weatherservice.dto.CityDTO;

public interface WeatherService {
	
	CityDTO getWeatherByCityName(String cityName, String appId) throws JsonProcessingException;
	CityDTO getWeatherByLatitudeAndLongitude(float latitude, float longitude, String appId) throws JsonProcessingException;
}