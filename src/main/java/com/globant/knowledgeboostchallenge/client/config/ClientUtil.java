package com.globant.knowledgeboostchallenge.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@Slf4j
@Configuration
public class ClientUtil {
    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders header = new HttpHeaders();
        header.set("Gerardo", "Gerardo");
        return header;
    }
    @Bean
    public HttpEntity<String> httpEntity() {
        HttpHeaders header = new HttpHeaders();
        header.set("Gerardo", "Gerardo");
        return new HttpEntity<>(header);
    }
}
