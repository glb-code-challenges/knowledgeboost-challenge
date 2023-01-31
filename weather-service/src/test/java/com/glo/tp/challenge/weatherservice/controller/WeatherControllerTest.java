package com.glo.tp.challenge.weatherservice.controller;

import com.glo.tp.challenge.weatherservice.services.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @MockBean
    WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetWeatherByCityNameIsOk() throws Exception{

        when(weatherService.getWeatherByCityName(any(), any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(get("/weatherservice/weather/city/Kansas")
                .param("accessToken","SJKLDQWA4SGDFSA")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetWeatherByLatAndLonIsOk() throws Exception{

        when(weatherService.getWeatherByLatitudeAndLongitude(any(), any(), any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(get("/weatherservice/weather/latitude/51.508/longitude/-0.1257")
                        .param("accessToken","SJKLDQWA4SGDFSA")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
