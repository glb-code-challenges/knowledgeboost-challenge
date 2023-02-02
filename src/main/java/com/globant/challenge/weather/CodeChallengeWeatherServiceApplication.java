package com.globant.challenge.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Wheater API", version = "1.0", description = "Wheater Information"))
public class CodeChallengeWeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeChallengeWeatherServiceApplication.class, args);
	}

}
