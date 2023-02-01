package com.glo.tp.challenge.weatherservice.services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.glo.tp.challenge.weatherservice.clients.WeatherClient;
import com.glo.tp.challenge.weatherservice.domain.WeatherHistory;
import com.glo.tp.challenge.weatherservice.dto.CityDTO;
import com.glo.tp.challenge.weatherservice.dto.FeignResponseExceptionDTO;
import com.glo.tp.challenge.weatherservice.repository.WeatherRepository;
import com.glo.tp.challenge.weatherservice.services.WeatherService;
import com.glo.tp.challenge.weatherservice.utils.WeatherUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
	
	private final WeatherRepository weatherRepository;
	
	private final WeatherClient weatherClient;
	

	@Override
	public CityDTO getWeatherByCityName(String cityName, String appid) throws JsonProcessingException {
		log.info("getWeatherByCityName {} {}", cityName, appid);
		
		ResponseEntity<CityDTO> response = null;
		
		try {
			response = weatherClient.getWeatherByCityNameFromApi(cityName, appid);
			WeatherHistory mappedEntity = WeatherUtils.buildWeatherCityInformation(response);
			saveBuiltEntity(mappedEntity);
		}
		catch(FeignException feignException) {
			log.error("Feign Exception: {} ", feignException.getMessage());
			FeignResponseExceptionDTO feignExceptionDetail = WeatherUtils.buildFeignResponseExceptionDTO(feignException);
			WeatherHistory mappedEntity = WeatherUtils.buildWeatherCityInformation(feignExceptionDetail, cityName);			
			saveBuiltEntity(mappedEntity);
			WeatherUtils.verifyAndThrowException(feignExceptionDetail);				
		}
		
		return response.getBody();
	}

	private void saveBuiltEntity(WeatherHistory mappedEntity) {
		//TODO: Validate JPA for possible exceptions
		weatherRepository.save(mappedEntity);
	}

	@Override
	public ResponseEntity<CityDTO> getWeatherByLatitudeAndLongitude(String latitude, String longitude, String appid) {
		log.info("getWeatherByLatitudeAndLongitude {} {} {}", latitude, longitude, appid);
		//TODO: Validate all the endpoint flow like the other one.
		return weatherClient.getWeatherByLatitudeAndLongitude(latitude, longitude, appid);
	}
}