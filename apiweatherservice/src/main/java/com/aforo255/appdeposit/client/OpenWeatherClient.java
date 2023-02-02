package com.aforo255.appdeposit.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import com.aforo255.appdeposit.client.dto.OpenWeatherResponse;
import com.aforo255.appdeposit.utils.Constants;
import com.aforo255.appdeposit.utils.Messages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OpenWeatherClient implements IOpenWeatherClient {
	
	Logger logger = LoggerFactory.getLogger(OpenWeatherClient.class);
	
	@Autowired
	@Qualifier("restTemplateCb")
	private RestTemplate restTemplate;
	
	@Autowired
	private CircuitBreakerFactory<?, ?> circuitBreakerFactory;
	
	@Autowired
	private Messages messages;

	@Override
	public ResponseEntity<OpenWeatherResponse> getWeatherByLatLong(String lat, String lon) {
		logger.info("Consuming OpeanWeatherApi with params lat: {} , lon: {}", lat, lon);
		ResponseEntity<OpenWeatherResponse> respuesta = null;
		
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("cb");
		
		StringBuilder sb = new StringBuilder(Constants.OPEN_WEATHER_BASE_URL);
		sb.append("?lat=");
		sb.append(lat);
		sb.append("&lon=");
		sb.append(lon);
		sb.append("&appid=");
		sb.append(Constants.APP_ID);		
			
		respuesta = circuitBreaker.run(()-> restTemplate.getForEntity(sb.toString(), OpenWeatherResponse.class), (t) -> getWeatherDefaultResponse(t));			
				
		return respuesta;
	}

	@Override
	public ResponseEntity<OpenWeatherResponse> getWeatherByCityName(String cityName) {
		logger.info("Consuming OpeanWeatherApi with params cityName: {}", cityName);
		ResponseEntity<OpenWeatherResponse> respuesta = null;
		
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("cb");
		
		StringBuilder sb = new StringBuilder(Constants.OPEN_WEATHER_BASE_URL);
		sb.append("?q=");
		sb.append(cityName);		
		sb.append("&appid=");
		sb.append(Constants.APP_ID);		
					
		respuesta = circuitBreaker.run(()-> restTemplate.getForEntity(sb.toString(), OpenWeatherResponse.class), (t) -> getWeatherDefaultResponse(t));
		
		return respuesta; 		
	}
	
	private ResponseEntity<OpenWeatherResponse> getWeatherDefaultResponse(Throwable t) {
		logger.error("Circuit breaker is acting!!");
		logger.error(t.getMessage());
		
		OpenWeatherResponse resp = new OpenWeatherResponse();
		HttpStatus status = null;
		if(t instanceof HttpClientErrorException.NotFound) {
			resp.setCod(Constants.HTTP_CODE_NOT_FOUND.toString());
			resp.setMessage(messages.get(Messages.Constants.RECORD_NOT_FOUND));
			status = HttpStatus.NOT_FOUND;
		} else if(t instanceof HttpClientErrorException) {
			String body = ((HttpClientErrorException)t).getResponseBodyAsString();
			if(body != null) {
				ObjectMapper om = new ObjectMapper();
				try {					
					resp = om.readValue(body, OpenWeatherResponse.class);
				} catch (JsonProcessingException e) {
					resp.setCod(Constants.HTTP_CODE_BAD_GATEWAY.toString());
					resp.setMessage(messages.get(Messages.Constants.READ_RESPONSE_ERROR).concat(t.getMessage()));
				}
			}
			status = HttpStatus.BAD_GATEWAY;
		} else {
			resp.setCod(Constants.HTTP_CODE_GENERAL_SERVER_ERROR.toString());
			resp.setMessage(t.getMessage());
			status = HttpStatus.BAD_GATEWAY;
		}		
		return new ResponseEntity<OpenWeatherResponse>(resp, status);
	}
	
}
