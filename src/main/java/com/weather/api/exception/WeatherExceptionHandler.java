package com.weather.api.exception;

import com.weather.api.record.ResponseRecord;
import com.weather.api.util.ResponseMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WeatherExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ResponseRecord<String>> notFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.FAILURE, notFoundException.getMessage(),
                notFoundException.getCause(), null),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ResponseRecord<String>> constraintException(ConstraintViolationException constraintViolationException) {
        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.FAILURE, constraintViolationException.getMessage(),
                constraintViolationException.getCause(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ResponseRecord<String>> runtimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.FAILURE, runtimeException.getMessage(),
                runtimeException.getCause(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
