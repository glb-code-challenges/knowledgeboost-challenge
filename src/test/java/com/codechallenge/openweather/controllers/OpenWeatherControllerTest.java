package com.codechallenge.openweather.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.codechallenge.openweather.models.WeatherBase;
import com.codechallenge.openweather.services.IOpenWeatherService;

@WebMvcTest(controllers = OpenWeatherController.class)
@AutoConfigureMockMvc
public class OpenWeatherControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IOpenWeatherService weatherService;
	
	@Test
	public void getWeatherByCity() throws Exception {
		WeatherBase weatherBase = new WeatherBase();
		weatherBase.setName("Tokyo");
		
		when(weatherService.getWeatherByCity(anyString())).thenReturn(weatherBase);
		
		mvc.perform(MockMvcRequestBuilders.get("/weather/city/{city}", "tokyo"))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
