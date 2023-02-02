package com.globant.challenge.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.globant.challenge.weather.model.dto.WheaterInfoResponse;

@FeignClient(name = "data", url = "https://api.openweathermap.org")
public interface WeatherClientRest {

	@GetMapping("/data/2.5/weather")
	WheaterInfoResponse getWeatherByCoordinates(@RequestParam("lat") String lat, @RequestParam("lon") String lon,
			@RequestParam("appid") String appId);

	@GetMapping("/data/2.5/weather")
	WheaterInfoResponse getWeatherByCityName(@RequestParam("q") String cityName, @RequestParam("appid") String appId);

}
