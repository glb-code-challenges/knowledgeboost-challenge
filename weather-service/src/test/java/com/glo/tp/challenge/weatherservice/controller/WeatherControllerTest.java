package com.glo.tp.challenge.weatherservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.glo.tp.challenge.weatherservice.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.glo.tp.challenge.weatherservice.services.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @MockBean
    WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestGetWeatherByCityNameIsOk() throws Exception{

        when(weatherService.getWeatherByCityName(any(), any())).thenReturn(TestUtils.buildCityDTO());

        mockMvc.perform(get("/weatherservice/weather/city/Kansas")
                .param("accessToken","SJKLDQWA4SGDFSA")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(TestUtils.getJsonStringFromFile("api_weather_ok.json"), true));
    }

    @Test
    public void TestGetWeatherByLatAndLonIsOk() throws Exception{

        when(weatherService.getWeatherByLatitudeAndLongitude(any(float.class), any(float.class), any())).thenReturn(TestUtils.buildCityDTO());

        mockMvc.perform(get("/weatherservice/weather/latitude/38.5003/longitude/-98.5006")
                        .param("accessToken","SJKLDQWA4SGDFSA")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(TestUtils.getJsonStringFromFile("api_weather_ok.json"), true));
    }
}
