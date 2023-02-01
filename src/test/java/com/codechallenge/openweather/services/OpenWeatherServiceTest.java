package com.codechallenge.openweather.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.codechallenge.openweather.models.WeatherBase;

@SpringBootTest
public class OpenWeatherServiceTest {
	
	@Autowired
	private IOpenWeatherService weatherService;

	@Test
	public void checkCityService() {
		final WeatherBase weatherBase = weatherService.getWeatherByCity("tokyo");
		Assertions.assertEquals("Tokyo", weatherBase.getName());
	}
	
	@Test
	public void checkLatLongService() {
		final WeatherBase weatherBase = weatherService.getWeatherByLatAndLong(15.0646, 120.7198);
		Assertions.assertEquals("Mexico", weatherBase.getName());
	}
	
}
