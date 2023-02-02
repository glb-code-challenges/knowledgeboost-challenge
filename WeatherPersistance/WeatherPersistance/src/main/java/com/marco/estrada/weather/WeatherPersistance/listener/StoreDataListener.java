package com.marco.estrada.weather.WeatherPersistance.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.estrada.weather.WeatherPersistance.dto.Request;
import com.marco.estrada.weather.WeatherPersistance.service.StoreDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "weather")
@Component
@RequiredArgsConstructor
public class StoreDataListener {

    private final StoreDataService service;

    @RabbitHandler
    public void startProcessData(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        service.storeRequestData(mapper.readValue(message, Request.class));
    }
}
