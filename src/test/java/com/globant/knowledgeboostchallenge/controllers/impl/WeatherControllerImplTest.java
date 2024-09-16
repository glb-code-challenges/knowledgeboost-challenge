package com.globant.knowledgeboostchallenge.controllers.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import com.globant.knowledgeboostchallenge.services.WeatherService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@Log4j2
@ExtendWith(MockitoExtension.class)
class WeatherControllerImplTest {
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String latitude="19.3584097";
    private static final String longitude="-99.0608733";
    private static final String appId="18e9633e9829e4d1b05adf1723de2bb1";
    private static final String cityName="London";


    @InjectMocks
    private WeatherControllerImpl weatherController;

    @Mock
    private WeatherService weatherService;

    private ResponseEntity<WeatherInfoResponse> expectedWeatherInfoResponseResponseEntity;


    @BeforeEach
    void setUp() throws IOException {
        WeatherInfoResponse info = OBJECT_MAPPER.readValue(
                WeatherControllerImplTest.class.getClassLoader().getResourceAsStream("weatherInfoResponse.json"), WeatherInfoResponse.class);
        expectedWeatherInfoResponseResponseEntity= new ResponseEntity(info, HttpStatus.OK);
    }

    @Test
    void getWeatherByCityAndAppId() {
        when(weatherService.getWeatherByCityAndAppId( cityName,  appId)).thenReturn(expectedWeatherInfoResponseResponseEntity);
        var actual =weatherController.getWeatherByCityAndAppId(cityName,appId);
        verify(weatherService, times(1)).getWeatherByCityAndAppId( cityName,  appId);
        assertEquals(expectedWeatherInfoResponseResponseEntity ,actual);
    }

    @Test
    void getWeatherByLatAndLonAndAppId() {
        when(weatherService.getWeatherByLatAndLonAndAppId( latitude,longitude,  appId)).thenReturn(expectedWeatherInfoResponseResponseEntity);
        var actual =weatherController.getWeatherByLatAndLonAndAppId(latitude,longitude,appId);
        verify(weatherService, times(1)).getWeatherByLatAndLonAndAppId( latitude,longitude,  appId);
        assertEquals(expectedWeatherInfoResponseResponseEntity ,actual);
    }
}