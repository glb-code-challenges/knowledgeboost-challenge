package com.glo.tp.challenge.weatherservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieves city information", content = { @Content(mediaType = "application/json", schema =  @Schema(implementation = CityDTO.class))}),
			@ApiResponse(responseCode = "401", description = "Invalid 'API Key'",  content = @Content),
			@ApiResponse(responseCode = "404", description = "City not found",  content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
	})
	@Operation(summary = "Retrieves city information with the 'cityName' param")
	@GetMapping("/city/{cityName}")
	public ResponseEntity<CityDTO> getWeatherByCityName(
			@PathVariable @NotNull @NotEmpty final String cityName,
			@RequestParam(value = "accessToken") String accessToken) throws JsonProcessingException {
		log.debug("getWeatherByCityName with  {}", cityName);
		return ResponseEntity.ok()
				.body(weatherService.getWeatherByCityName(cityName, accessToken));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieves city information"),
			@ApiResponse(responseCode = "401", description = "Invalid 'API Key'"),
			@ApiResponse(responseCode = "404", description = "City not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@Operation(summary = "Retrieves city information with the 'latitude' and 'longitude' params")
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