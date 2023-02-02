package com.globant.challenge.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.globant.challenge.weather.client.WeatherClientRest;
import com.globant.challenge.weather.dao.IWeatherRequestLogDao;
import com.globant.challenge.weather.model.dto.WheaterInfoResponse;
import com.globant.challenge.weather.model.entity.WeatherRequestLog;

@Service
public class WeatherServiceFeign implements IWeatherService {

	@Value("${openweather.api.app.id}")
	private String appId;

	@Autowired
	private WeatherClientRest weatherClient;
	@Autowired
	private IWeatherRequestLogDao weatherRequestLogDao;

	@Override
	public WheaterInfoResponse getWeatherByCoordinates(String lat, String lon) throws Exception {
		try {
			WheaterInfoResponse response = weatherClient.getWeatherByCoordinates(lat, lon, appId);
			weatherRequestLogDao.save(WeatherRequestLog.builder().cityName(response.getName())
					.responseCode(response.getCod()).responseMessage("Successful Request").build());

			return response;
		} catch (Exception ex) {
			throw new Exception("An error occured while request weather api ");
		}
	}

	@Override
	public WheaterInfoResponse getWeatherByCityName(String cityName) throws Exception {
		try {
			WheaterInfoResponse response = weatherClient.getWeatherByCityName(cityName, appId);
			weatherRequestLogDao.save(WeatherRequestLog.builder().cityName(response.getName())
					.responseCode(response.getCod()).responseMessage("Successful Request").build());
			return response;
		} catch (Exception ex) {
			throw new Exception("An error occured while request weather api ");
		}

	}

}
