package com.weatherapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapi.model.dto.CityWeather;
import com.weatherapi.model.dto.CoordinatesWeather;
import com.weatherapi.service.WeatherService;

@RestController
@RequestMapping("weather/")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;

	@GetMapping(value = "{cityName}")
	public ResponseEntity<CityWeather> getCityWeather(@PathVariable String cityName) {
		
		return new ResponseEntity<>(weatherService.getCityWeather(cityName), HttpStatus.OK);
	}
	
	@GetMapping(value = "{lat}/{lon}")
	public ResponseEntity<CoordinatesWeather> getCoordinatesWeather(@PathVariable String lat, @PathVariable String lon) {
		
		return new ResponseEntity<>(weatherService.getCoordinatesWeather(lat, lon), HttpStatus.OK);
	}
	
}
