package com.codechallenge.openweather.services;

import java.util.Date;
import java.util.List;

import com.codechallenge.openweather.models.WeatherBase;
import com.codechallenge.openweather.models.entity.WeatherData;

public interface IOpenWeatherService {

	public WeatherBase getWeatherByCity(String cityName);
	
	public WeatherBase getWeatherByLatAndLong(Double latitude, Double longitude);
	
	public void saveErrorTransaction(String city, String errorMessage);
	
	public List<WeatherData> findByCreateDate(Date from, Date to);
	
}
