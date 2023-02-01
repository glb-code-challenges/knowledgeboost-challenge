package com.codechallenge.openweather.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.codechallenge.openweather.models.WeatherBase;
import com.codechallenge.openweather.models.entity.WeatherData;
import com.codechallenge.openweather.services.IOpenWeatherService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class OpenWeatherController {

	private final Logger logger = LoggerFactory.getLogger(OpenWeatherController.class);
	@Autowired
	private IOpenWeatherService weatherService;
	
	@CircuitBreaker(name = "cities", fallbackMethod = "alternativeMethod")
	@GetMapping("/weather/city/{city}")
	public ResponseEntity<?> getByCityName(@PathVariable(name = "city", required = true) String city) throws InterruptedException {
		
		if ("errorcity".equals(city.toLowerCase())) {
			logger.info("Simulando error para validar circuit breaker.");
			throw new IllegalStateException("La ciudad no fue encontrada.");
		}
		
		if ("juarez".equals(city.toLowerCase())) {
			logger.info("Simulando retraso en la respuesta para validar circuit breaker.");
			TimeUnit.SECONDS.sleep(5L);
		}
		
		WeatherBase weatherBase = weatherService.getWeatherByCity(city);
		return new ResponseEntity<>(weatherBase, HttpStatus.OK);
	}
	
	@CircuitBreaker(name = "cities", fallbackMethod = "alternativeMethodLatLong")
	@GetMapping("/weather/latitude/{lat}/longitude/{long}")
	public ResponseEntity<?> getByCityNameAndCountry(@PathVariable(name = "lat", required = true) Double latitude,
			@PathVariable(name = "long", required = true) Double longitude) {
		WeatherBase weatherBase = weatherService.getWeatherByLatAndLong(latitude, longitude);
		return new ResponseEntity<>(weatherBase, HttpStatus.OK);
	}
	
	@GetMapping("/weather/list")
	public ResponseEntity<?> getRequestsList(
			@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
		List<WeatherData> weatherData = weatherService.findByCreateDate(from, to);
		return new ResponseEntity<>(weatherData, HttpStatus.OK);
	}
	
	public ResponseEntity<?> alternativeMethod(String city, Throwable e) {
		logger.info("Hubo un error durante la llamada al endpoint city. " + e.getMessage());
		Map<String, Object> mapReturn = new HashMap<>();
		mapReturn.put("message", e.getMessage());
		mapReturn.put("city", city);
		mapReturn.put("description", "Hubo un error durante la llamada al endpoint city.");
		
		return new ResponseEntity<>(mapReturn, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> alternativeMethodLatLong(String city, Throwable e) {
		logger.info("Hubo un error durante la llamada al endpoint Lat - Long. " + e.getMessage());
		Map<String, Object> mapReturn = new HashMap<>();
		mapReturn.put("message", e.getMessage());
		mapReturn.put("city", city);
		mapReturn.put("description", "Hubo un error durante la llamada al endpoint Lat - Long.");
		
		return new ResponseEntity<>(mapReturn, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
