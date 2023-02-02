package com.aforo255.appdeposit.exception;

public class OpenWeatherException extends RuntimeException {

	private static final long serialVersionUID = -3040146168192850859L;

	public OpenWeatherException() {
		super();
	}

	public OpenWeatherException(String message, Throwable cause) {
		super(message, cause);
	}

	public OpenWeatherException(String message) {
		super(message);
	}

	public OpenWeatherException(Throwable cause) {
		super(cause);
	}

}
