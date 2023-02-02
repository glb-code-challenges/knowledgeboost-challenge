package com.marco.estrada.weather.WeatherOrchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WeatherOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherOrchestratorApplication.class, args);
	}

}
