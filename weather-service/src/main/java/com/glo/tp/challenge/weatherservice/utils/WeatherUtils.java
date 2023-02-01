package com.glo.tp.challenge.weatherservice.utils;

import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glo.tp.challenge.weatherservice.domain.WeatherHistory;
import com.glo.tp.challenge.weatherservice.dto.CityDTO;
import com.glo.tp.challenge.weatherservice.dto.FeignResponseExceptionDTO;
import com.glo.tp.challenge.weatherservice.exception.CityNotFoundException;
import com.glo.tp.challenge.weatherservice.exception.InvalidAccessException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeatherUtils {

	private static final String NO_ASSIGNED_CITY_NAME = "N/A";
	
	public static WeatherHistory buildWeatherCityInformation(FeignResponseExceptionDTO feignExceptionDetail, String cityName) {
		return WeatherHistory.builder()
				.consultedBy("City Name")
				.code(feignExceptionDetail.getCod())
				.cityName(cityName)
				.message(feignExceptionDetail.getMessage())
				.operationDate(LocalDate.now())					
				.build();		
	}

	public static WeatherHistory buildWeatherCityInformation(FeignResponseExceptionDTO feignExceptionDetail, float latitude, float longitude) {
		return WeatherHistory.builder()
				.consultedBy("Latitude and Longitude")
				.code(feignExceptionDetail.getCod())
				.cityName(NO_ASSIGNED_CITY_NAME)
				.longitude(longitude)
				.latitude(latitude)
				.message(feignExceptionDetail.getMessage())
				.operationDate(LocalDate.now())
				.build();
	}
	
	public static WeatherHistory buildWeatherCityInformation(CityDTO response, String field) throws JsonProcessingException {

		return WeatherHistory.builder()
				.consultedBy(field)
				.cityName(response.getName())
				.latitude(response.getCoord().getLat())
				.longitude(response.getCoord().getLon())
				.code(response.getCod())
				.operationDate(LocalDate.now())
				.message(WeatherUtils.convertResponseToJson(response))
				.build();
	}
	
	public static String convertResponseToJson(CityDTO responseDetail) throws JsonProcessingException {
		return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(responseDetail);
	}
	
	public static FeignResponseExceptionDTO buildFeignResponseExceptionDTO(FeignException feignException) throws JsonProcessingException {
		return new ObjectMapper().readValue(feignException.contentUTF8(), FeignResponseExceptionDTO.class);
	}
	
	public static void verifyAndThrowException(FeignResponseExceptionDTO feignException) {
		log.error("The error code was: {} with message: {}", feignException.getCod(), feignException.getMessage());
		if(feignException.getCod() == 401) {
			throw new InvalidAccessException(feignException.getCod() + " " + feignException.getMessage());
		}
		else if(feignException.getCod() == 404) {
			throw new CityNotFoundException(feignException.getCod() + " " + feignException.getMessage());
		}
		else {
			throw new RuntimeException(feignException.getCod() + " " + feignException.getMessage());
		}
	}
}