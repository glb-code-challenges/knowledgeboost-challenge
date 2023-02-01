package com.glo.tp.challenge.weatherservice.exception;

public class CityNotFoundException extends RuntimeException {
	
	private String message;
	
	public CityNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
