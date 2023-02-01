package com.globant.weather.controller;

import com.globant.weather.dto.ErrorResponse;
import com.globant.weather.exeption.WeatherException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, null, LocalDateTime.now());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().stream().forEach((error) -> {
            String propertyPath = error.getPropertyPath().toString();
            errors.add(propertyPath.substring(propertyPath.indexOf(".") + 1) + ": [" + error.getInvalidValue() + "] " + error.getMessage());
        });
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errors, LocalDateTime.now());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<Object> handleWeatherException(WeatherException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getErrors(), LocalDateTime.now());
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse error) {
        return new ResponseEntity<Object>(error, error.getStatus());
    }
}
