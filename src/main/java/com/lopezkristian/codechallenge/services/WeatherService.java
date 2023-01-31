package com.lopezkristian.codechallenge.services;

import com.lopezkristian.codechallenge.model.LogWeather;
import com.lopezkristian.codechallenge.model.OpenWeather;
import com.lopezkristian.codechallenge.repository.LogWeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Slf4j
public class WeatherService implements IWeatherService {

    final LogWeatherRepository logWeatherRepository;

    final RestTemplate restTemplate;

    @Value("${open.weather.api.key}")
    private String api;

    @Value("${open.weather.base.url}")
    private String baseUrl;

    public WeatherService(LogWeatherRepository logWeatherRepository, RestTemplateBuilder restTemplate) {
        this.logWeatherRepository = logWeatherRepository;
        this.restTemplate = restTemplate.build();
    }

    public LogWeather getCity(String cityName) {

        LogWeather logWeather = LogWeather.builder()
                .timestamp(new Timestamp(new Date().getTime()))
                .build();

        saveTransaction(logWeather);

        StringBuilder urlRequest = new StringBuilder(baseUrl);

        urlRequest.append("?q=").append(cityName)
                .append("&appid=").append(api);

        try {
            OpenWeather response = restTemplate.getForObject(urlRequest.toString(), OpenWeather.class);
            if (response != null) {
                log.info(response.toString());
                logWeather.setCityName(response.name);
                logWeather.setCodeResponse(String.valueOf(response.cod));

                saveTransaction(logWeather);
            }

        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            logWeather.setCityName(cityName);
            logWeather.setError(e.getMessage());
            logWeather.setCodeResponse(HttpStatus.BAD_REQUEST.toString());
            errorTransaction(logWeather);
        }
        return logWeather;

    }

    public LogWeather getWeatherLatitudeLongitude(String latitude, String longitude) {

        LogWeather logWeather = new LogWeather();
        logWeather.setTimestamp(new Timestamp(new Date().getTime()));
        saveTransaction(logWeather);

        StringBuilder urlRequest = new StringBuilder(baseUrl);

        urlRequest.append("?lat=").append(latitude)
                .append("&lon=").append(longitude)
                .append("&appid=").append(api);
        try {
            OpenWeather response = restTemplate.getForObject(urlRequest.toString(), OpenWeather.class);
            if (response != null) {
                log.info(response.toString());
                logWeather.setCityName(response.name);
                logWeather.setCodeResponse(String.valueOf(response.cod));

                saveTransaction(logWeather);
            }

        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            logWeather.setError(e.getMessage());
            logWeather.setCodeResponse(HttpStatus.BAD_REQUEST.toString());
            errorTransaction(logWeather);
        }

        return logWeather;
    }

    @Override
    public void saveTransaction(LogWeather logWeather) {
        logWeatherRepository.save(logWeather);
    }

    @Override
    public void errorTransaction(LogWeather logWeather) {
        saveTransaction(logWeather);
    }

}
