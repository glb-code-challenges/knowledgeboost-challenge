package com.codechallenge.openweather.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.codechallenge.openweather.models.WeatherBase;
import com.codechallenge.openweather.models.dto.WeatherDao;
import com.codechallenge.openweather.models.entity.WeatherData;
import com.codechallenge.openweather.services.clients.OpenWeatherFeignClient;

@Service
public class OpenWeatherService implements IOpenWeatherService {
	@Autowired
	private OpenWeatherFeignClient weatherFeignClient;
	@Autowired
	private WeatherDao weatherDao;
	
	@Value("${openweather.api.key}")
	private String apiKey;
	
	public WeatherBase getWeatherByCity(String cityName) {
		WeatherBase weather = weatherFeignClient.getWeatherByCity(cityName, apiKey);
		saveTransaction(weather);
		return weather;
	}

	@Override
	public WeatherBase getWeatherByLatAndLong(Double latitude, Double longitude) {
		WeatherBase weather = weatherFeignClient.getWeatherByCityAndCountry(latitude, longitude, apiKey);
		saveTransaction(weather);
		return weather;
	}
	
	private void saveTransaction(WeatherBase weatherBase) {
		WeatherData weatherData = new WeatherData();
		weatherData.setCityName(weatherBase.getName());
		weatherData.setCreateAt(new Date());
		weatherData.setResponse(weatherBase.getCod().toString());
		weatherDao.save(weatherData);
	}

	@Override
	public void saveErrorTransaction(String city, String errorMessage) {
		WeatherData weatherData = new WeatherData();
		weatherData.setCityName(city);
		weatherData.setCreateAt(new Date());
		weatherData.setResponse(errorMessage);
		weatherDao.save(weatherData);
	}

	@Override
	public List<WeatherData> findByCreateDate(Date from, Date to) {
		return weatherDao.findByCreateAtBetween(from, to);
	}


}
