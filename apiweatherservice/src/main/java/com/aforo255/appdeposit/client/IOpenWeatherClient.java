package com.aforo255.appdeposit.client;

import org.springframework.http.ResponseEntity;

import com.aforo255.appdeposit.client.dto.OpenWeatherResponse;

public interface IOpenWeatherClient {

	public ResponseEntity<OpenWeatherResponse> getWeatherByLatLong(String lat, String lon);
	public ResponseEntity<OpenWeatherResponse> getWeatherByCityName(String cityName);
	
}
