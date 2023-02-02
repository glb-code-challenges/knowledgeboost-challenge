package com.weather.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KnowledgeboostChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeboostChallengeApplication.class, args);
	}

}
