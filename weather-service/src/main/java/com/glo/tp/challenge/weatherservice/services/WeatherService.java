package com.glo.tp.challenge.weatherservice.services;

import org.springframework.http.ResponseEntity;

public interface WeatherService {
	
	public ResponseEntity<?> getWeatherByCityName(String cityName, String appid);
	public ResponseEntity<?> getWeatherByLatitudeAndLongitude(String latitude, String longitude, String appid);

}
