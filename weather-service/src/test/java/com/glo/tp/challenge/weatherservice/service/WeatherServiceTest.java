package com.glo.tp.challenge.weatherservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.glo.tp.challenge.weatherservice.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.glo.tp.challenge.weatherservice.clients.WeatherClient;
import com.glo.tp.challenge.weatherservice.dto.CityDTO;
import com.glo.tp.challenge.weatherservice.repository.WeatherRepository;
import com.glo.tp.challenge.weatherservice.services.WeatherService;
import com.glo.tp.challenge.weatherservice.services.impl.WeatherServiceImpl;

@RunWith(SpringRunner.class)
public class WeatherServiceTest {
	
	private WeatherService weatherService;
	
	@MockBean
	WeatherRepository weatherRepository;
	
	@MockBean
	WeatherClient weatherClient;


	private final String ACCESS_TOKEN = "Access_Token";

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		weatherService = new WeatherServiceImpl(weatherRepository, weatherClient);
	}
	
	@Test
	public void TestWeatherClientByCityNameOk() throws JsonProcessingException {
		
		when(weatherClient.getWeatherByCityNameFromApi(any(String.class), any(String.class))).thenReturn(new ResponseEntity<>(TestUtils.buildCityDTO(), HttpStatus.OK));
		when(weatherRepository.save(any())).thenReturn(TestUtils.buildWeatherEntityObject());
		
		CityDTO result = weatherService.getWeatherByCityName("MyCity", ACCESS_TOKEN);
		
		assertNotNull(result);
		assertEquals(result.getId(), 4273857);
		assertEquals(result.getName(), "Kansas");
		assertEquals(result.getCod(), 200);
		assertEquals(result.getBase(), "stations");
		assertEquals(result.getVisibility(), 10000);
		assertEquals(result.getDt(), 1675267108);
		assertEquals(result.getTimeZone(), -21600);

	}

	@Test
	public void TestWeatherClientByLatAndLongOk() throws JsonProcessingException {
		
		when(weatherClient.getWeatherByLatitudeAndLongitude(any(float.class), any(float.class), any(String.class))).thenReturn(new ResponseEntity<>(TestUtils.buildCityDTO(), HttpStatus.OK));
		when(weatherRepository.save(any())).thenReturn(TestUtils.buildWeatherEntityObject());

		CityDTO result = weatherService.getWeatherByLatitudeAndLongitude(15.0646f, 120.7198f, ACCESS_TOKEN);
		
		assertNotNull(result);
		assertEquals(result.getId(), 4273857);
		assertEquals(result.getName(), "Kansas");
		assertEquals(result.getCod(), 200);
		assertEquals(result.getBase(), "stations");
		assertEquals(result.getVisibility(), 10000);
		assertEquals(result.getDt(), 1675267108);
		assertEquals(result.getTimeZone(), -21600);
	}
}
