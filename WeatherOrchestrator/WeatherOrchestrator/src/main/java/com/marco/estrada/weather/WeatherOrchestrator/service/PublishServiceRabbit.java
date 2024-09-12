package com.marco.estrada.weather.WeatherOrchestrator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.estrada.weather.WeatherOrchestrator.dto.Request;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile("local")
@Slf4j
@RequiredArgsConstructor
@Service
public class PublishServiceRabbit implements PublishInterface{

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    private final ObjectMapper mapper;


    public void sentMessagePersist(Request request) {
        try {
            rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(request));
        } catch (JsonProcessingException exception){
            log.error("Error serializing message", exception);
        }

    }

}
