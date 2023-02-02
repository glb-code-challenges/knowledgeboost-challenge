package com.marco.estrada.weather.WeatherOrchestrator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("weather");
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
