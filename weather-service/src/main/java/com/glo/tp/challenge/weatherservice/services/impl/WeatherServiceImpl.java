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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
	
	private final WeatherRepository weatherRepository;
	
	private final WeatherClient weatherClient;
	

	@Override
	public CityDTO getWeatherByCityName(String cityName, String appId) throws JsonProcessingException {
		log.info("getWeatherByCityName {} {}", cityName, appId);

		CityDTO response = CityDTO.builder().build();
		
		try {
			response = Optional.ofNullable(weatherClient.getWeatherByCityNameFromApi(cityName, appId))
					.filter(re -> re.getStatusCodeValue() == 200)
					.map(ResponseEntity::getBody)
					.orElseThrow(RuntimeException::new);

			WeatherHistory mappedEntity = WeatherUtils.buildWeatherCityInformation(response, "City Name");
			saveBuiltEntity(mappedEntity);
		}
		catch(FeignException feignException) {
			log.error("Feign Exception: {} ", feignException.getMessage());
			FeignResponseExceptionDTO feignExceptionDetail = WeatherUtils.buildFeignResponseExceptionDTO(feignException);
			WeatherHistory mappedEntity = WeatherUtils.buildWeatherCityInformation(feignExceptionDetail, cityName);			
			saveBuiltEntity(mappedEntity);
			WeatherUtils.verifyAndThrowException(feignExceptionDetail);				
		}
		
		return response;
	}

	@Override
	public CityDTO getWeatherByLatitudeAndLongitude(float latitude, float longitude, String appId) throws  JsonProcessingException{
		log.info("getWeatherByLatitudeAndLongitude {} {} {}", latitude, longitude, appId);

		CityDTO response = CityDTO.builder().build();

		try{
			response = Optional.ofNullable(weatherClient.getWeatherByLatitudeAndLongitude(latitude, longitude, appId))
					.filter(re -> re.getStatusCodeValue() == 200)
					.map(ResponseEntity::getBody)
					.orElseThrow(RuntimeException::new);

			WeatherHistory mappedEntity = WeatherUtils.buildWeatherCityInformation(response, "Latitude and Longitude");
			saveBuiltEntity(mappedEntity);
		}
		catch(FeignException feignException){
			FeignResponseExceptionDTO feignExceptionDetail = WeatherUtils.buildFeignResponseExceptionDTO(feignException);
			WeatherHistory mappedEntity = WeatherUtils.buildWeatherCityInformation(feignExceptionDetail, latitude, longitude);
			saveBuiltEntity(mappedEntity);
			WeatherUtils.verifyAndThrowException(feignExceptionDetail);
		}
		return response;
	}

	private void saveBuiltEntity(WeatherHistory mappedEntity) {
		weatherRepository.save(mappedEntity);
	}
}