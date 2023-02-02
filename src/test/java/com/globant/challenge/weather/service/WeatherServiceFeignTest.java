package com.globant.challenge.weather.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.globant.challenge.weather.client.WeatherClientRest;
import com.globant.challenge.weather.dao.IWeatherRequestLogDao;
import com.globant.challenge.weather.model.dto.CloudInformation;
import com.globant.challenge.weather.model.dto.Coordinates;
import com.globant.challenge.weather.model.dto.MainInformation;
import com.globant.challenge.weather.model.dto.SysInformation;
import com.globant.challenge.weather.model.dto.WeatherResume;
import com.globant.challenge.weather.model.dto.WheaterInfoResponse;
import com.globant.challenge.weather.model.dto.WindInformation;
import com.globant.challenge.weather.model.entity.WeatherRequestLog;

@SpringBootTest
public class WeatherServiceFeignTest {

	@Autowired
	IWeatherService weatherService;
	@MockBean
	WeatherClientRest weatherClient;
	@MockBean
	IWeatherRequestLogDao weatherRequestLogDao;

	@Test
	void getWeatherByCoordinates() {

		// given
		var lat = "19.451054";
		var lon = "99.125519";
		List<WeatherResume> weather = List.of(WeatherResume.builder().id(12).main("main test")
				.description("Wheather resume description test").icon("Wheater resume icon test").build());
		var base = "base test";
		var main = MainInformation.builder().temp(2.03).feelsLike(1.02).tempMin(7.3).tempMax(5.5).pressure(4)
				.humidity(6).seaLevel(3).grndLevel(9).build();
		var visibility = 123;
		var wind = WindInformation.builder().speed(25.9).deg(3).gust(8.8).build();
		var clouds = CloudInformation.builder().all(1).build();
		var dt = 45;
		var sys = SysInformation.builder().type(1).id(4).country("Country test").sunrise(2).sunset(9).build();
		var timezone = 87;
		var id = 5;
		var name = "Name test";
		var cod = 4357;
		var requestLogId = UUID.randomUUID();
		var weatherRequestLog = WeatherRequestLog.builder().id(requestLogId).cityName(name).createdAt(new Date())
				.responseCode(200).responseMessage("message test").build();

		WheaterInfoResponse weatherInfo = WheaterInfoResponse.builder()
				.coord(Coordinates.builder().lat(lat).lon(lon).build()).weather(weather).base(base).main(main)
				.visibility(visibility).wind(wind).clouds(clouds).dt(dt).sys(sys).timezone(timezone).id(id).name(name)
				.cod(cod).build();

		// when
		when(weatherClient.getWeatherByCoordinates(anyString(), anyString(), anyString())).thenReturn(weatherInfo);
		when(weatherRequestLogDao.save(any())).thenReturn(weatherRequestLog);
		WheaterInfoResponse response = weatherService.getWeatherByCoordinates(lat, lon);

		// then
		assertNotNull(response);
		assertNotNull(response.getCoord());
		assertNotNull(response.getWeather());
		assertNotNull(response.getBase());
		assertNotNull(response.getMain());
		assertNotNull(response.getVisibility());
		assertNotNull(response.getWind());
		assertNotNull(response.getClouds());
		assertNotNull(response.getDt());
		assertNotNull(response.getSys());
		assertNotNull(response.getTimezone());
		assertNotNull(response.getId());
		assertNotNull(response.getName());
		assertNotNull(response.getCod());

		verify(weatherClient, times(1)).getWeatherByCoordinates(anyString(), anyString(), anyString());
		verify(weatherRequestLogDao, times(1)).save(any());
	}

	@Test
	void getWeatherByCityName() {

		// given
		var lat = "19.451054";
		var lon = "99.125519";
		List<WeatherResume> weather = List.of(WeatherResume.builder().id(12).main("main test")
				.description("Wheather resume description test").icon("Wheater resume icon test").build());
		var base = "base test";
		var main = MainInformation.builder().temp(2.03).feelsLike(1.02).tempMin(7.3).tempMax(5.5).pressure(4)
				.humidity(6).seaLevel(3).grndLevel(9).build();
		var visibility = 123;
		var wind = WindInformation.builder().speed(25.9).deg(3).gust(8.8).build();
		var clouds = CloudInformation.builder().all(1).build();
		var dt = 45;
		var sys = SysInformation.builder().type(1).id(4).country("Country test").sunrise(2).sunset(9).build();
		var timezone = 87;
		var id = 5;
		var name = "Name test";
		var cod = 4357;
		var requestLogId = UUID.randomUUID();
		var weatherRequestLog = WeatherRequestLog.builder().id(requestLogId).cityName(name).createdAt(new Date())
				.responseCode(200).responseMessage("message test").build();

		WheaterInfoResponse weatherInfo = WheaterInfoResponse.builder()
				.coord(Coordinates.builder().lat(lat).lon(lon).build()).weather(weather).base(base).main(main)
				.visibility(visibility).wind(wind).clouds(clouds).dt(dt).sys(sys).timezone(timezone).id(id).name(name)
				.cod(cod).build();

		// when
		when(weatherClient.getWeatherByCityName(anyString(), anyString())).thenReturn(weatherInfo);
		when(weatherRequestLogDao.save(any())).thenReturn(weatherRequestLog);
		WheaterInfoResponse response = weatherService.getWeatherByCityName(name);

		// then
		assertNotNull(response);
		assertNotNull(response.getCoord());
		assertNotNull(response.getWeather());
		assertNotNull(response.getBase());
		assertNotNull(response.getMain());
		assertNotNull(response.getVisibility());
		assertNotNull(response.getWind());
		assertNotNull(response.getClouds());
		assertNotNull(response.getDt());
		assertNotNull(response.getSys());
		assertNotNull(response.getTimezone());
		assertNotNull(response.getId());
		assertNotNull(response.getName());
		assertNotNull(response.getCod());

		verify(weatherClient, times(1)).getWeatherByCityName(anyString(), anyString());
		verify(weatherRequestLogDao, times(1)).save(any());
	}

}
