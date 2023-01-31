package com.lopezkristian.codechallenge.controller;

import com.lopezkristian.codechallenge.model.LogWeather;
import com.lopezkristian.codechallenge.services.IWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IWeatherService weatherService;

    @Test
    void cityNameEmpty() throws Exception {
        LogWeather logWeather = new LogWeather();
        logWeather.setCityName("");
        when(weatherService.getCity(anyString())).thenReturn(logWeather);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/city/{cityName}", ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCity() throws Exception {
        LogWeather logWeather = new LogWeather();
        logWeather.setCityName("London");
        when(weatherService.getCity(anyString())).thenReturn(logWeather);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/city/{cityName}", "london"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherLatitudeLongitude() throws Exception {
        LogWeather logWeather = new LogWeather();
        logWeather.setCityName("London");
        when(weatherService.getWeatherLatitudeLongitude(anyString(), anyString())).thenReturn(logWeather);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/latitude/{latitude}/longitude/{longitude}", "5", "10"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}