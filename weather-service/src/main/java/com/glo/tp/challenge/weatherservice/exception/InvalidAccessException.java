package com.glo.tp.challenge.weatherservice.exception;

public class InvalidAccessException extends RuntimeException {
	
	private String message;
	
	public InvalidAccessException(String message) {
		super(message);
		this.message = message;
	}
}
