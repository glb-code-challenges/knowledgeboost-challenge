package com.aforo255.appdeposit.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aforo255.appdeposit.client.IOpenWeatherClient;
import com.aforo255.appdeposit.client.dto.OpenWeatherResponse;
import com.aforo255.appdeposit.exception.NotFoundException;
import com.aforo255.appdeposit.exception.OpenWeatherException;
import com.aforo255.appdeposit.message.WeatherMessagePublish;
import com.aforo255.appdeposit.model.WeatherModel;
import com.aforo255.appdeposit.repository.IWeatherRepository;
import com.aforo255.appdeposit.utils.Messages;

@Service
public class WeatherService implements IWeatherService {

	@Autowired
	private IWeatherRepository weatherRepository;

	@Autowired
	private IOpenWeatherClient openWeatherClient;
	
	@Autowired
	private WeatherMessagePublish messageEvent;
	
	@Autowired
	private Messages messages;

	@Override
	public WeatherModel getByLatLong(String lat, String lon) {		

		ResponseEntity<OpenWeatherResponse> openWeatherResponse = openWeatherClient.getWeatherByLatLong(lat, lon);
		WeatherModel model = new WeatherModel();
		model.setLat(lat);
		model.setLon(lon);
		model.setTimestamp(LocalDateTime.now());
		if (openWeatherResponse.getStatusCode().equals(HttpStatus.OK)) {
			model.setMessage(messages.get(Messages.Constants.OPERATION_SUCCESS));			
			model.setResponse(openWeatherResponse.getBody());								
			messageEvent.sendWeatherEvent(model);
		} else if (openWeatherResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			model.setMessage(openWeatherResponse.getBody().getMessage());						
			model.setResponse(null);
			messageEvent.sendWeatherEvent(model);
			throw new NotFoundException(openWeatherResponse.getBody().getMessage());
		} else {
			model.setMessage(messages.get(Messages.Constants.OPERATION_ERROR).concat(openWeatherResponse.getBody().getMessage()));			
			model.setResponse(null);				
			messageEvent.sendWeatherEvent(model);
			throw new OpenWeatherException(openWeatherResponse.getBody().getMessage());
		}
		return model;
	}

	@Override
	public WeatherModel getByCityName(String cityName) {
	
		ResponseEntity<OpenWeatherResponse> openWeatherResponse = openWeatherClient.getWeatherByCityName(cityName);
		WeatherModel model = new WeatherModel();
		model.setCityName(cityName);
		model.setTimestamp(LocalDateTime.now());
		if (openWeatherResponse.getStatusCode().equals(HttpStatus.OK)) {
			model.setMessage(messages.get(Messages.Constants.OPERATION_SUCCESS));
			model.setResponse(openWeatherResponse.getBody());
			model = weatherRepository.insert(model);
		} else if (openWeatherResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			model.setMessage(openWeatherResponse.getBody().getMessage());
			model.setResponse(null);
			model = weatherRepository.insert(model);
			throw new NotFoundException(openWeatherResponse.getBody().getMessage());
		} else {
			model.setMessage(messages.get(Messages.Constants.OPERATION_ERROR).concat(openWeatherResponse.getBody().getMessage()));
			model.setResponse(null);
			model = weatherRepository.insert(model);
			throw new OpenWeatherException(openWeatherResponse.getBody().getMessage());
		}
		return model;
	}
}