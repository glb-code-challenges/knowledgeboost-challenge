package com.glo.tp.challenge.weatherservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.glo.tp.challenge.weatherservice.services.WeatherService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/weatherservice/weather")
@RequiredArgsConstructor
public class WeatherController {

	private final WeatherService weatherService;

	@GetMapping("/city/{cityName}")
	public ResponseEntity<?> getWeatherByCityName(@PathVariable final String cityName, @RequestParam(value = "accessToken") String accessToken) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(weatherService.getWeatherByCityName(cityName, accessToken));
	}

	@GetMapping("/latitude/{latitude}/longitude/{longitude}")
	public ResponseEntity<?> getWeatherByLatitudeAndLongitude(@PathVariable final String latitude, @PathVariable final String longitude, @RequestParam(value = "accessToken") String accessToken) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(weatherService.getWeatherByLatitudeAndLongitude(latitude, longitude,accessToken));
	}
}