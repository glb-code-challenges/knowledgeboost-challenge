package com.weather.api.service.impl;

import com.weather.api.client.OpenWeatherApiClient;
import com.weather.api.client.response.WeatherResponse;
import com.weather.api.converter.GenericBuilder;
import com.weather.api.converter.WeatherDTOToExecutionEntityConverter;
import com.weather.api.entity.ExecutionEntity;
import com.weather.api.exception.NotFoundException;
import com.weather.api.record.WeatherAndDBDataRecord;
import com.weather.api.repository.ExecutionsRepository;
import com.weather.api.service.WeatherService;
import com.weather.api.util.Constants;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private final OpenWeatherApiClient openWeatherApiClient;
    private final ExecutionsRepository executionsRepository;
    private final WeatherDTOToExecutionEntityConverter weatherDTOToExecutionEntityConverter;
    private static final String APP_ID = System.getenv("APP_ID_VALUE");


    @Override
    public WeatherAndDBDataRecord<WeatherResponse, ExecutionEntity> getWeatherByCityName(String cityName) {
        try {
            WeatherResponse weatherResponse = openWeatherApiClient.getWeatherByCityName(cityName, APP_ID);
            return new WeatherAndDBDataRecord<>(executionsRepository.save(weatherDTOToExecutionEntityConverter.convert(weatherResponse)), weatherResponse);
        } catch (FeignException feignException) {
            log.error("{}, http-response: {}", Constants.CITY_NOT_FOUND, feignException.status());
            executionsRepository.save(GenericBuilder.of(ExecutionEntity::new)
                    .map(ExecutionEntity::setDatetime, LocalDateTime::now)
                    .map(ExecutionEntity::setResponseCode, feignException::status)
                    .map(ExecutionEntity::setRootCause, feignException::contentUTF8)
                    .map(ExecutionEntity::setCityName, () -> cityName)
                    .build()
            );
            throw new NotFoundException(Constants.CITY_NOT_FOUND, null);
        }
    }

    @Override
    public WeatherAndDBDataRecord<WeatherResponse, ExecutionEntity> getWeatherByLatitudeAndLongitude(Double latitude, Double longitude) {
        try {
            WeatherResponse weatherResponse = openWeatherApiClient.getWeatherByLatitudeAndLongitude(latitude, longitude, APP_ID);
            return new WeatherAndDBDataRecord<>(executionsRepository.save(weatherDTOToExecutionEntityConverter.convert(weatherResponse)), weatherResponse);
        } catch (FeignException feignException) {
            log.error("{}, http-response: {}", Constants.LATITUDE_AND_LONGITUDE_NOT_FOUND, feignException.status());
            executionsRepository.save(GenericBuilder.of(ExecutionEntity::new)
                    .map(ExecutionEntity::setDatetime, LocalDateTime::now)
                    .map(ExecutionEntity::setResponseCode, feignException::status)
                    .map(ExecutionEntity::setRootCause, feignException::contentUTF8)
                    .build()
            );

            throw new NotFoundException(Constants.LATITUDE_AND_LONGITUDE_NOT_FOUND, null);
        }
    }
}
