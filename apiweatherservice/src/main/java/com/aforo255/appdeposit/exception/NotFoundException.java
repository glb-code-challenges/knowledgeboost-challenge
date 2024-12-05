package com.aforo255.appdeposit.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3040146168192850859L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
