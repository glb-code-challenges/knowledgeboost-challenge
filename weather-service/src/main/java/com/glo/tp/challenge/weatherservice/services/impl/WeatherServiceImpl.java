package com.glo.tp.challenge.weatherservice.services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.glo.tp.challenge.weatherservice.clients.WeatherClient;
import com.glo.tp.challenge.weatherservice.repository.WeatherRepository;
import com.glo.tp.challenge.weatherservice.services.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
	
	private final WeatherRepository weatherRepository;
	
	private final WeatherClient weatherClient;
	

	@Override
	public ResponseEntity<?> getWeatherByCityName(String cityName, String appid) {
		log.info("getWeatherByCityName {} {}", cityName, appid);
		// TODO: After get info from API, save the data
		return weatherClient.getWeatherByCityNameFromApi(cityName, appid);
	}


	@Override
	public ResponseEntity<?> getWeatherByLatitudeAndLongitude(String latitude, String longitude, String appid) {
		log.info("getWeatherByLatitudeAndLongitude {} {} {}", latitude, longitude, appid);
		//TODO: After get info from API, save the data
		return weatherClient.getWeatherByLatitudeAndLongitude(latitude, longitude, appid);
	}

}
