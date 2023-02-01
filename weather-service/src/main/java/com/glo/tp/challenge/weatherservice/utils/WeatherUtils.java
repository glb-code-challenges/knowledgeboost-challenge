package com.glo.tp.challenge.weatherservice.utils;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	
	public static WeatherHistory buildWeatherCityInformation(FeignResponseExceptionDTO feignExceptionDetail, String cityName) {
		return WeatherHistory.builder()
				.code(feignExceptionDetail.getCod())
				.cityName(cityName)
				.message(feignExceptionDetail.getMessage())
				.operationDate(LocalDate.now())					
				.build();		
	}
	
	public static WeatherHistory buildWeatherCityInformation(ResponseEntity<CityDTO> response) throws JsonProcessingException {
		
		CityDTO responseDetail = response.getBody();
		
		return WeatherHistory.builder()
				.cityName(responseDetail.getName())
				.code(responseDetail.getCod())
				.operationDate(LocalDate.now())
				.message(WeatherUtils.convertResponseToJson(responseDetail))
				.build();
	}
	
	public static String convertResponseToJson(CityDTO responseDetail) throws JsonProcessingException {
		return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(responseDetail);
	}
	
	public static FeignResponseExceptionDTO buildFeignResponseExceptionDTO(FeignException feignException) throws JsonMappingException, JsonProcessingException {
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