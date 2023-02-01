package com.weatherapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weatherapi.model.dto.CityWeather;
import com.weatherapi.model.dto.CoordinatesWeather;

@Service
public class WeatherService {
	
	private final static String byCityBaseUrl = "https://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=dfa00e9af5287ca9c7446d6a16dcbbee";
	private final static String byCoordinatesBaseUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=dfa00e9af5287ca9c7446d6a16dcbbee";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public CityWeather getCityWeather(String cityName) {
		
		String url = byCityBaseUrl.replace("{cityName}", cityName);
		
		 CityWeather weather = restTemplate.getForObject(url, CityWeather.class);

		return weather;
	}

	public CoordinatesWeather getCoordinatesWeather(String lat, String lon) {
		String url = byCoordinatesBaseUrl.replace("{lon}", lon).replace("{lat}", lat);
		
		 CoordinatesWeather coordinatesWeather = restTemplate.getForObject(url, CoordinatesWeather.class);

		return coordinatesWeather;
	}

}
