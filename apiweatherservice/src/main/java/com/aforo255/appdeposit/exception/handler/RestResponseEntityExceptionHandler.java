package com.aforo255.appdeposit.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aforo255.appdeposit.dto.ApiWeatherServiceResponse;
import com.aforo255.appdeposit.exception.NotFoundException;
import com.aforo255.appdeposit.exception.OpenWeatherException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { OpenWeatherException.class })
	protected ResponseEntity<ApiWeatherServiceResponse> handleOpenWeatherApiException(OpenWeatherException ex, WebRequest request) {
		ApiWeatherServiceResponse response = new ApiWeatherServiceResponse();
		response.setCode("APIWTH003");
		response.setMessage("Error de consumo a OpenWeatherAPI: " + ex.getMessage());		
		return new ResponseEntity<ApiWeatherServiceResponse>(response, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<ApiWeatherServiceResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
		ApiWeatherServiceResponse response = new ApiWeatherServiceResponse();
		response.setCode("APIWTH004");
		response.setMessage(ex.getMessage());		
		return new ResponseEntity<ApiWeatherServiceResponse>(response, HttpStatus.NOT_FOUND);
	}
}
