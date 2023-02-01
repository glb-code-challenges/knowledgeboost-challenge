package com.weather.api.service.impl;

import com.weather.api.client.OpenWeatherApiClient;
import com.weather.api.client.response.WeatherResponse;
import com.weather.api.exception.NotFoundException;
import com.weather.api.service.WeatherService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private final OpenWeatherApiClient openWeatherApiClient;
    private static final String APP_ID = System.getenv("APP_ID_VALUE");
    private static final String CITY_NOT_FOUND = "City was not found";
    private static final String LATITUDE_AND_LONGITUDE_NOT_FOUND = "Latitude and longitude were not found";

    @Override
    public WeatherResponse getWeatherByCityName(String cityName) {
            try {
                return openWeatherApiClient.getWeatherByCityName(cityName, APP_ID);
            }catch (FeignException feignException) {
                log.error("{}, http-response: {}", CITY_NOT_FOUND, feignException.status());
                throw new NotFoundException(CITY_NOT_FOUND, null);
            }
    }

    @Override
    public WeatherResponse getWeatherByLatitudeAndLongitude(Double latitude, Double longitude) {
        try {
            return openWeatherApiClient.getWeatherByLatitudeAndLongitude(latitude, longitude, APP_ID);
        }catch (FeignException feignException) {
            log.error("{}, http-response: {}", LATITUDE_AND_LONGITUDE_NOT_FOUND, feignException.status());
            throw new NotFoundException(LATITUDE_AND_LONGITUDE_NOT_FOUND, null);
        }
    }
}
