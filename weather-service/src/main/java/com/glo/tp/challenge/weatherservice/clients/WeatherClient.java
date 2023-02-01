package com.glo.tp.challenge.weatherservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glo.tp.challenge.weatherservice.dto.CityDTO;

@FeignClient(name = "weather", url="https://api.openweathermap.org/data/2.5")
public interface WeatherClient {
	
	@GetMapping("/weather")
	ResponseEntity<CityDTO> getWeatherByCityNameFromApi(@RequestParam(value = "q") String q, @RequestParam(value = "appid") String appid);
	
	@GetMapping("/weather")
	ResponseEntity<CityDTO> getWeatherByLatitudeAndLongitude(@RequestParam(value = "lat") String lat, @RequestParam(value = "lon") String lon, @RequestParam(value = "appid") String appid);
}