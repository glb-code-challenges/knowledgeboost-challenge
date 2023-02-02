package com.weather.api.controller;

import com.weather.api.client.response.WeatherResponse;
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
                .thenReturn(new WeatherAndDBDataRecord<>(new ExecutionEntity(), new WeatherResponse()));

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
                .thenReturn(new WeatherAndDBDataRecord<>(new ExecutionEntity(), new WeatherResponse()));

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

}