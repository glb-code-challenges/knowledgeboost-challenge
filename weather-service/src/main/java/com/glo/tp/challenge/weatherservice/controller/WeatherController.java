package com.glo.tp.challenge.weatherservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.glo.tp.challenge.weatherservice.dto.CityDTO;
import com.glo.tp.challenge.weatherservice.services.WeatherService;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;

@RestController
@RequestMapping("/weatherservice/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

	private final WeatherService weatherService;

	@GetMapping("/city/{cityName}")
	public ResponseEntity<CityDTO> getWeatherByCityName(
			@PathVariable @NotNull @NotEmpty final String cityName,
			@RequestParam(value = "accessToken") String accessToken) throws JsonProcessingException {
		log.debug("getWeatherByCityName with  {}", cityName);
		return ResponseEntity.ok()
				.body(weatherService.getWeatherByCityName(cityName, accessToken));
	}

	@GetMapping("/latitude/{latitude}/longitude/{longitude}")
	public ResponseEntity<CityDTO> getWeatherByLatitudeAndLongitude(
			@PathVariable @NotNull @NotEmpty Float latitude,
			@PathVariable @NotNull @NotEmpty Float longitude,
			@RequestParam(value = "accessToken") String accessToken) throws JsonProcessingException {
		log.debug("getWeatherByLatitudeAndLongitude with latitude {} and longitude {}", latitude, longitude);
		return ResponseEntity.ok()
				.body(weatherService.getWeatherByLatitudeAndLongitude(latitude, longitude,accessToken));
	}
}