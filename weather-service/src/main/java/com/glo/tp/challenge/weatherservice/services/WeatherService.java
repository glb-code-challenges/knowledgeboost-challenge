package com.glo.tp.challenge.weatherservice.services;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.glo.tp.challenge.weatherservice.dto.CityDTO;

public interface WeatherService {
	
	public CityDTO getWeatherByCityName(String cityName, String appid) throws JsonProcessingException;
	public ResponseEntity<CityDTO> getWeatherByLatitudeAndLongitude(String latitude, String longitude, String appid);
}