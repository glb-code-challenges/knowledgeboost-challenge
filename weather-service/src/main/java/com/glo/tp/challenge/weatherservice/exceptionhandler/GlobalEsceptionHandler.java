package com.glo.tp.challenge.weatherservice.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.glo.tp.challenge.weatherservice.exception.CityNotFoundException;
import com.glo.tp.challenge.weatherservice.exception.InvalidAccessException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalEsceptionHandler {
	
	
	@ExceptionHandler(InvalidAccessException.class)
	public ResponseEntity<String> handleInvalidAccessException(InvalidAccessException ex){
		log.error("Exception Caught in handleCityInfoNotFoundException : {} ", ex.getMessage(), ex);
		 return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}
	
	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<String> handleCityInfoNotFoundException(CityNotFoundException ex){
		log.error("Exception Caught in handleCityInfoNotFoundException : {} ", ex.getMessage(), ex);
		 return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
		log.error("Exception Caught in handleCityInfoNotFoundException : {} ", ex.getMessage(), ex);
		 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}