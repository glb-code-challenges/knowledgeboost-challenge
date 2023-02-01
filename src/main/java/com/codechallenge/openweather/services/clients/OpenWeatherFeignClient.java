package com.codechallenge.openweather.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.codechallenge.openweather.models.WeatherBase;

@FeignClient(name = "openweatherClient", url = "${openweather.api.url}")
public interface OpenWeatherFeignClient {

	@GetMapping("/data/2.5/weather?q={cityName}&appid={apiKey}&units=metric")
	public WeatherBase getWeatherByCity(@PathVariable String cityName,
			@PathVariable String apiKey);
	
	@GetMapping("/data/2.5/weather?lat={latitude}&lon={longitude}&appid={apiKey}")
	public WeatherBase getWeatherByCityAndCountry(@PathVariable Double latitude, 
			@PathVariable Double longitude, 
			@PathVariable String apiKey);
	
}
