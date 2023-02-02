package com.globant.challenge.weather.service;

import com.globant.challenge.weather.model.dto.WheaterInfoResponse;

public interface IWeatherService {

	WheaterInfoResponse getWeatherByCoordinates(String lat, String lon) throws Exception;

	WheaterInfoResponse getWeatherByCityName(String cityName) throws Exception;

}
