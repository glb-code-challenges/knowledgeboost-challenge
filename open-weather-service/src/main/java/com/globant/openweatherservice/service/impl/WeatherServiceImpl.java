package com.globant.openweatherservice.service.impl;

import com.globant.openweatherservice.dto.response.WeatherResponseDto;
import com.globant.openweatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${open-weather.base-url}")
    private String OPEN_WEATHER_URL;

    @Value("${open-weather.app-id}")
    private String OPEN_WEATHER_APP_ID;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public WeatherResponseDto cityWeatherRequest(String city) {
        String uri = OPEN_WEATHER_URL.concat("q=").concat(city).concat("&appid=").concat(OPEN_WEATHER_APP_ID);
        ResponseEntity<WeatherResponseDto> response = restTemplate.getForEntity(uri, WeatherResponseDto.class);
        return response.getBody();
    }

    @Override
    public WeatherResponseDto latitudeLongitudeWeatherRequest(Float latitude, Float longitude) {
        String uri = OPEN_WEATHER_URL.concat("lat=").concat(latitude.toString()).concat("&lon=").concat(longitude.toString()).concat("&appid=").concat(OPEN_WEATHER_APP_ID);
        ResponseEntity<WeatherResponseDto> response = restTemplate.getForEntity(uri, WeatherResponseDto.class);
        return response.getBody();
    }
}
