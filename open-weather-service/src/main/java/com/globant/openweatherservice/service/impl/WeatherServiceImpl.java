package com.globant.openweatherservice.service.impl;

import com.globant.openweatherservice.dao.OperationDao;
import com.globant.openweatherservice.dto.response.WeatherResponseDto;
import com.globant.openweatherservice.entity.Operation;
import com.globant.openweatherservice.exception.InvalidDataException;
import com.globant.openweatherservice.service.WeatherService;
import com.globant.openweatherservice.utils.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    @Value("${open-weather.base-url}")
    private String OPEN_WEATHER_URL;

    @Value("${open-weather.app-id}")
    private String OPEN_WEATHER_APP_ID;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OperationDao operationDao;

    @Override
    public WeatherResponseDto cityWeatherRequest(String city) throws InvalidDataException{
        String uri = OPEN_WEATHER_URL.concat("q=").concat(city).concat("&appid=").concat(OPEN_WEATHER_APP_ID);
        ResponseEntity<WeatherResponseDto> response = restTemplate.getForEntity(uri, WeatherResponseDto.class);
        saveWeatherOperation(response.getBody().getName(), response.getBody().getCod().toString(), "NA");
        return response.getBody();
    }

    @Override
    public WeatherResponseDto latitudeLongitudeWeatherRequest(Float latitude, Float longitude) {
        String uri = OPEN_WEATHER_URL.concat("lat=").concat(latitude.toString()).concat("&lon=").concat(longitude.toString()).concat("&appid=").concat(OPEN_WEATHER_APP_ID);
        ResponseEntity<WeatherResponseDto> response = restTemplate.getForEntity(uri, WeatherResponseDto.class);
        saveWeatherOperation(response.getBody().getName(), response.getBody().getCod().toString(), "NA");
        return response.getBody();
    }

    @Override
    public void saveWeatherOperation(String cityName, String responseCode, String rootCause){
        Operation operation = new Operation();
        operation.setTimestamp(Timestamp.from(Instant.now()));
        operation.setCityName(cityName);
        operation.setResponseCode(responseCode);
        operation.setRootCause(rootCause);
        operationDao.save(operation);
    }
}
