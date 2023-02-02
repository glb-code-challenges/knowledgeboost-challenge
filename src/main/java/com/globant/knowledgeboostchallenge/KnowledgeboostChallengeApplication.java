package com.globant.knowledgeboostchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class KnowledgeboostChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeboostChallengeApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplateBuilder builder = new RestTemplateBuilder();
		return builder.setConnectTimeout(Duration.ofSeconds(5)).setReadTimeout(Duration.ofSeconds(5)).build();
	}

}
