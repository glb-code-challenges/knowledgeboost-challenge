package com.globant.challenge.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.weather.client.WeatherClientRest;
import com.globant.challenge.weather.dao.IWeatherRequestLogDao;
import com.globant.challenge.weather.model.dto.WheaterInfoResponse;
import com.globant.challenge.weather.model.entity.WeatherRequestLog;

@Service
public class WeatherServiceFeign implements IWeatherService {

	private static final String APP_ID = "484f91cafdcb92966b787def3fa03ade";

	@Autowired
	private WeatherClientRest weatherClient;
	@Autowired
	private IWeatherRequestLogDao weatherRequestLogDao;

	@Override
	public WheaterInfoResponse getWeatherByCoordinates(String lat, String lon) {

		WheaterInfoResponse response = weatherClient.getWeatherByCoordinates(lat, lon, APP_ID);
		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName(response.getName())
				.responseCode(response.getCod()).responseMessage("Successful Request").build());

		return response;
	}

	@Override
	public WheaterInfoResponse getWeatherByCityName(String cityName) {

		WheaterInfoResponse response = weatherClient.getWeatherByCityName(cityName, APP_ID);
		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName(response.getName())
				.responseCode(response.getCod()).responseMessage("Successful Request").build());

		return response;
	}

}
