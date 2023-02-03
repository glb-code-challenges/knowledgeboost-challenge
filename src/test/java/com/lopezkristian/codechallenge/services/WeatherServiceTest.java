package com.lopezkristian.codechallenge.services;

import com.lopezkristian.codechallenge.model.LogWeather;
import com.lopezkristian.codechallenge.model.OpenWeather;
import com.lopezkristian.codechallenge.repository.LogWeatherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private IWeatherService weatherService;

    @Mock
    private LogWeatherRepository logWeatherRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getCity() {
        LogWeather logWeather = weatherService.getCity("London");
        when(restTemplate.getForObject(anyString(), any())).thenReturn(new OpenWeather());
        System.out.println(logWeather);
        assertNotNull(logWeather);
        assertEquals("London", logWeather.getCityName());
    }

    @Test
    void getWeatherLatitudeLongitude() {
        LogWeather logWeather = weatherService.getWeatherLatitudeLongitude("44.34", "10.99");
        assertNotNull(logWeather);
        assertEquals("Zocca", logWeather.getCityName());
    }


}