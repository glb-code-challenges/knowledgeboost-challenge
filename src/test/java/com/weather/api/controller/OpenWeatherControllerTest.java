package com.weather.api.controller;

import com.weather.api.client.response.*;
import com.weather.api.entity.ExecutionEntity;
import com.weather.api.exception.NotFoundException;
import com.weather.api.record.WeatherAndDBDataRecord;
import com.weather.api.service.WeatherService;
import com.weather.api.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OpenWeatherController.class)
class OpenWeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void arrangeObtainWeather_whenCityNameIsValid_thenHttpStatusOk() throws Exception {
        when(weatherService.getWeatherByCityName(anyString()))
                .thenReturn(new WeatherAndDBDataRecord<>(generateExecutionEntity(), generateWeatherResponse()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/city/Mexico city"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void arrangeValidateException_whenNotExistCityName_thenHttpStatusNotFound() throws Exception {
        when(weatherService.getWeatherByCityName(anyString()))
                .thenThrow(new NotFoundException(Constants.CITY_NOT_FOUND, null));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/city/City doesnt exist"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void arrangeValidateConstraint_whenCityNameIsEmpty_thenHttpStatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/city/  "))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void arrangeObtainWeather_whenCorrectLatitudeAndLongitude_thenHttpStatusOk() throws Exception {
        when(weatherService.getWeatherByLatitudeAndLongitude(anyDouble(), anyDouble()))
                .thenReturn(new WeatherAndDBDataRecord<>(generateExecutionEntity(), generateWeatherResponse()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/latitude/19.4285/longitude/-99.1277"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void arrangeValidateException_whenNoExistLatitudeAndLongitude_thenHttpStatusNotFound() throws Exception {
        when(weatherService.getWeatherByLatitudeAndLongitude(anyDouble(), anyDouble()))
                .thenThrow(new NotFoundException(Constants.LATITUDE_AND_LONGITUDE_NOT_FOUND, null));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/latitude/19.4285/longitude/-99.1277"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void arrangeValidateConstraint_whenLocationAndLatitudeAreLowerThatAllowed_thenHttpStatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/latitude/-99.0/longitude/-189.0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void arrangeValidateConstraint_whenLocationAndLatitudeAreLargerThatAllowed_thenHttpStatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/latitude/99.0/longitude/189.0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void arrangeValidateRuntimeException_whenGeneralErrorOccurs_thenHttpStatusInternalServerError() throws Exception {
        when(weatherService.getWeatherByLatitudeAndLongitude(anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException(""));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/weather/latitude/19.4285/longitude/-99.1277"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    private ExecutionEntity generateExecutionEntity() {
        ExecutionEntity executionEntity = new ExecutionEntity();
        executionEntity.setDatetime(LocalDateTime.now());
        executionEntity.setResponseCode(200);
        executionEntity.setRootCause("Successful request");
        executionEntity.setCityName("Mexico City");
        return executionEntity;
    }

    private WeatherResponse generateWeatherResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setBase("stations");
        weatherResponse.setCod(200);
        weatherResponse.setId(1234);
        weatherResponse.setName("city name");
        weatherResponse.setTimezone(3600);
        weatherResponse.setDt(1675311836);

        Coord coord = new Coord();
        coord.setLat(12.34);
        coord.setLon(56.78);
        weatherResponse.setCoord(coord);

        Main main = new Main();
        main.setHumidity(80);
        main.setPressure(1013);
        main.setTemp(293.15);
        main.setTempMax(294.15);
        main.setTempMin(292.15);
        weatherResponse.setMain(main);
        main.setFeelsLike(288.04);

        Weather weather = new Weather();
        weather.setDescription("clear sky");
        weather.setId(800);
        weather.setMain("Clear");
        weather.setIcon("01d");
        weatherResponse.setWeather(Arrays.asList(weather));

        Wind wind = new Wind();
        wind.setDeg(90);
        wind.setSpeed(5.1);
        weatherResponse.setWind(wind);

        Clouds clouds = new Clouds();
        clouds.setAll(0);
        weatherResponse.setClouds(clouds);

        Sys sys = new Sys();
        sys.setCountry("US");
        sys.setSunrise(1601234567);
        sys.setSunset(1601234567);
        sys.setType(2);
        sys.setId(47729);
        weatherResponse.setSys(sys);

        weatherResponse.setVisibility(10000);
        return weatherResponse;
    }
}