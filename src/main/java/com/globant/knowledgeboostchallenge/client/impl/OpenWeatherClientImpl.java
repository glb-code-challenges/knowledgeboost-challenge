package com.globant.knowledgeboostchallenge.client.impl;

import com.globant.knowledgeboostchallenge.client.OpenWeatherClient;

import com.globant.knowledgeboostchallenge.dao.dtos.WeatherInfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class OpenWeatherClientImpl implements OpenWeatherClient {

    //@Value("${url.open_weather}")
    private static final String urlRequest="http://api.openweathermap.org/data/2.5/weather";
    private final RestTemplate restTemplate;
    private final HttpEntity<String> httpEntity;

    public OpenWeatherClientImpl(RestTemplate restTemplate, HttpEntity<String> httpEntity) {
        this.restTemplate = restTemplate;
        this.httpEntity = httpEntity;
    }

    @Override
    public ResponseEntity<WeatherInfoDto> getWeatherByLatAndLonAndAppId(String lat, String lon, String appId) {
        URI targetUrl = UriComponentsBuilder.fromHttpUrl(urlRequest)
                .queryParam("lat",lat)
                .queryParam("lon",lon)
                .queryParam("appId", appId).build(true).toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, null, WeatherInfoDto.class);
    }

    @Override
    public ResponseEntity<WeatherInfoDto> getWeatherByCityAndAppId(String city, String appId) {
        URI targetUrl = UriComponentsBuilder.fromHttpUrl(urlRequest)
                .queryParam("q",city)
                .queryParam("appId", appId).build(true).toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, null, WeatherInfoDto.class);
    }
}
