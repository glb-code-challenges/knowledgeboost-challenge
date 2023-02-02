package com.globant.challenge.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.weather.model.dto.WheaterInfoResponse;
import com.globant.challenge.weather.service.IWeatherService;

@RestController
@RequestMapping("weather")
public class WeatherController {

	private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	private IWeatherService weatherService;

	@GetMapping("/latitude/{latitude}/longitude/{longitude}")
	public ResponseEntity<WheaterInfoResponse> getWeatherByCoordinates(@PathVariable("latitude") Double lat,
			@PathVariable("longitude") Double lon) {
		log.info(String.format("requesting weather by Coordinates latitude: %s - longitude: %s", lat, lon));
		return ResponseEntity.ok(weatherService.getWeatherByCoordinates(lat.toString(), lon.toString()));
	}

	@GetMapping("/city/{cityName}")
	public ResponseEntity<WheaterInfoResponse> getWeatherByCityName(@PathVariable("cityName") String cityName) {
		log.info(String.format("requesting weather by cityName: %s", cityName));
		return ResponseEntity.ok(weatherService.getWeatherByCityName(cityName));
	}
}
