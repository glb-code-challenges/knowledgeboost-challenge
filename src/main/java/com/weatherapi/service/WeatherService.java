package com.weatherapi.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weatherapi.model.dto.CityWeatherDto;
import com.weatherapi.model.dto.CoordinatesWeather;
import com.weatherapi.model.entity.WeatherEntity;
import com.weatherapi.repository.WeatherRepository;

@Service
public class WeatherService {
	
	private final static String byCityBaseUrl = "https://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=dfa00e9af5287ca9c7446d6a16dcbbee";
	private final static String byCoordinatesBaseUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=dfa00e9af5287ca9c7446d6a16dcbbee";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	public CityWeatherDto getCityWeather(String cityName) {
		
		String url = byCityBaseUrl.replace("{cityName}", cityName);
		
		 CityWeatherDto weatherDto = restTemplate.getForObject(url, CityWeatherDto.class);
		 
		 weatherRepository.save(mapDtoToEntity(weatherDto));
		 
		return weatherDto;
	}

	public CoordinatesWeather getCoordinatesWeather(String lat, String lon) {
		String url = byCoordinatesBaseUrl.replace("{lon}", lon).replace("{lat}", lat);
		
		 CoordinatesWeather coordinatesWeather = restTemplate.getForObject(url, CoordinatesWeather.class);
		 

		return coordinatesWeather;
	}
	
	private WeatherEntity mapDtoToEntity(CityWeatherDto weatherDto) {
		 WeatherEntity entity = new WeatherEntity();
		 entity.setCityName(weatherDto.getCityName());
		 entity.setResponseCode(weatherDto.getResponseCode());
		 entity.setCreatedOn(LocalDateTime.now());
		 return entity;
	}

}
