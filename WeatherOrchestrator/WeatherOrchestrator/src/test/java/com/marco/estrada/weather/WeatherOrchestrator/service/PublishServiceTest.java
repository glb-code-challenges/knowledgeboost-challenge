package com.marco.estrada.weather.WeatherOrchestrator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.estrada.weather.WeatherOrchestrator.dto.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@ExtendWith(MockitoExtension.class)
class PublishServiceTest {

  @InjectMocks
  PublishService publishService;

  @Mock
  RabbitTemplate rabbitTemplate;

  @Mock
  Queue queue;

  @Mock
  ObjectMapper mapper;


  @Test
  void sentMessagePersist() {
    publishService.sentMessagePersist(Request.builder().build());
  }
}