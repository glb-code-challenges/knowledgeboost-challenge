package com.aforo255.appdeposit.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aforo255.appdeposit.dto.ApiWeatherServiceResponse;
import com.aforo255.appdeposit.metrics.IncreaseCounterDeposit;
import com.aforo255.appdeposit.model.WeatherModel;
import com.aforo255.appdeposit.service.IWeatherService;
import com.aforo255.appdeposit.utils.Constants;
import com.aforo255.appdeposit.utils.Messages;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	IWeatherService weatherService;
	
	@Autowired
	IncreaseCounterDeposit increaseCounterDeposit;
	
	@Autowired
	private Messages messages;

	@GetMapping("/{lat}/{lon}")
	public ResponseEntity<ApiWeatherServiceResponse> getWeatherByLatLon(@PathVariable @Pattern(regexp=Constants.REGEX_LATITUDE) String lat, @PathVariable @Pattern(regexp = Constants.REGEX_LONGITUDE) String lon) {
		logger.info("Get: weatherByLatLong Lat: {} - Lon: {}", lat, lon);		
		WeatherModel weather = weatherService.getByLatLong(lat, lon);
		ApiWeatherServiceResponse response = new ApiWeatherServiceResponse();
		response.setCode(messages.get(Messages.Constants.SEARCH_SUCCESS_CODE));
		response.setMessage(messages.get(Messages.Constants.SEARCH_SUCCESS_MESSAGE));
		response.setWeather(weather);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{cityName}")
	public ResponseEntity<ApiWeatherServiceResponse> getWeatherByCityName(@PathVariable @NotBlank String cityName) {
		logger.info("Get: weatherByCityName cityName: {}", cityName);		
		WeatherModel weather = weatherService.getByCityName(cityName);
		ApiWeatherServiceResponse response = new ApiWeatherServiceResponse();
		response.setCode(messages.get(Messages.Constants.SEARCH_SUCCESS_CODE));
		response.setMessage(messages.get(Messages.Constants.SEARCH_SUCCESS_MESSAGE));
		response.setWeather(weather);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
