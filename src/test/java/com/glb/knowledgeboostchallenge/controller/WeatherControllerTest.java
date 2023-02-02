package com.glb.knowledgeboostchallenge.controller;

import com.glb.knowledgeboostchallenge.AbstractTest;
import com.glb.knowledgeboostchallenge.repository.WeatherRepository;
import com.glb.knowledgeboostchallenge.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.glb.knowledgeboostchallenge.utils.Constants.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = WeatherController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class WeatherControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @Test
    public void When_ValidCountry_Expected_WeatherData() throws Exception {
        when(weatherService.getWeatherByCity(createRequestByCity()))
            .thenReturn(createOkResponse());
        mvc.perform(MockMvcRequestBuilders
                .get(WEATHER_API + GET_BY_NAME, "London")
                .header("apiKey", "6949ac5e4c9b64212d689f28b7c40263")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.countryCode").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.countryCode").value("GB"));
    }

    @Test
    public void When_ValidCoordinates_Expected_WeatherData() throws Exception {
        when(weatherService.getWeatherByCoordinates(createRequestByCoordinates()))
            .thenReturn(createOkResponse());
        mvc.perform(MockMvcRequestBuilders
                .get(WEATHER_API + GET_BY_COORDINATES, "44.34", "10.99")
                .header("apiKey", "6949ac5e4c9b64212d689f28b7c40263")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.countryCode").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.countryCode").value("GB"));
    }

    @Test
    public void When_NotValidCountry_Expected_CityNotFound() throws Exception {
        when(weatherService.getWeatherByCity(createRequestByWrongCity()))
            .thenReturn(createNotFoundCity());
        mvc.perform(MockMvcRequestBuilders
                .get(WEATHER_API + GET_BY_NAME, "1")
                .header("apiKey", "6949ac5e4c9b64212d689f28b7c40263")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(MESSAGE_CITY_NOT_FOUND));
    }
}
