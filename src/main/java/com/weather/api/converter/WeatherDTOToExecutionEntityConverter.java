package com.weather.api.converter;

import com.weather.api.client.response.WeatherResponse;
import com.weather.api.entity.ExecutionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherDTOToExecutionEntityConverter implements GenericConverter<WeatherResponse, ExecutionEntity> {
    @Override
    public ExecutionEntity convert(WeatherResponse source) {
        return GenericBuilder.of(ExecutionEntity::new)
                .map(ExecutionEntity::setDatetime, LocalDateTime::now)
                .map(ExecutionEntity::setResponseCode, source::getCod)
                .map(ExecutionEntity::setCityName, source::getName)
                .build();
    }
}
