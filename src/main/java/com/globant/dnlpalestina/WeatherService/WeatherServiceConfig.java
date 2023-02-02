package com.globant.dnlpalestina.WeatherService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherServiceConfig {
    @Bean("restTemplate")
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }
}
