package com.weather.api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
