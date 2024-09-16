package com.globant.weather.service;

import com.globant.weather.dto.WeatherResponse;
import com.globant.weather.exeption.WeatherException;
import com.globant.weather.repository.IWeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImpTest {
    @Mock
    private IWeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImp weatherServiceImp;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(weatherServiceImp, "url", System.getProperty("WEATHER_URL"));
        ReflectionTestUtils.setField(weatherServiceImp, "appId", System.getProperty("WEATHER_APPID");
    }

    @Test
    void givenCityWhenFoundThen() throws WeatherException {
       WeatherResponse response = this.weatherServiceImp.getByCityName("Mexico City,mx");
       assertEquals(response.getName(),"Mexico City");
    }

    @Test
    void givenCoordinatesWhenFoundthen() throws WeatherException {
        WeatherResponse response = this.weatherServiceImp.getByCoordinates("51.5085","-0.1257");
        assertEquals(response.getName(),"London");
    }
    @Test()
    void givenCityWhenNotFoundThen() {
        int httpStatus = 0;
        try {
            WeatherResponse response = this.weatherServiceImp.getByCityName("fake city");
        } catch (WeatherException e){
            httpStatus = e.getCode().value();
        }
        assertEquals(httpStatus,HttpStatus.NOT_FOUND.value());
    }

    @Test()
    void givenCoordinatesWhenNotFoundThen() {
        int httpStatus = 0;
        try {
            WeatherResponse response = this.weatherServiceImp.getByCoordinates("-200","300");
        } catch (WeatherException e){
            httpStatus = e.getCode().value();
        }
        assertEquals(httpStatus,HttpStatus.BAD_REQUEST.value());
    }
}