package com.glo.tp.challenge.weatherservice.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.glo.tp.challenge.weatherservice.clients.WeatherClient;
import com.glo.tp.challenge.weatherservice.domain.WeatherHistory;
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
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		weatherService = new WeatherServiceImpl(weatherRepository, weatherClient);
	}
	
	@Test
	public void testWeatherClientByCityNameOk() {
		
		when(weatherClient.getWeatherByCityNameFromApi(any(String.class), any(String.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		//when(weatherRepository.save(any())).thenReturn(buildWeatherEntityObject());
		
		ResponseEntity<?> result = weatherService.getWeatherByCityName("MyCity", "wqnvcsrkjfnjkAHJKGSHKGS");
		
		assertNotNull(result);
		assertNotNull(result.getStatusCode());
		
	}
	
	@Test
	public void testWeatherClientByLatAndLongOk() {
		
		when(weatherClient.getWeatherByLatitudeAndLongitude(any(String.class), any(String.class), any(String.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		//when(weatherRepository.save(any())).thenReturn(buildWeatherEntityObject());
		
		ResponseEntity<?> result = weatherService.getWeatherByLatitudeAndLongitude("15.0646", "120.7198", "wqnvcsrkjfnjkAHJKGSHKGS");
		
		assertNotNull(result);
		assertNotNull(result.getStatusCode());
		
	}

	private WeatherHistory buildWeatherEntityObject() {
		return WeatherHistory.builder()
				.id(1L)
				.cityName("Kansas")
				.code(200)
				.message("Ok")
				.operationDate(LocalDate.now())
				.build();
	}
}
