package com.globant.openweatherservice.controller;

import com.globant.openweatherservice.dto.ErrorDto;
import com.globant.openweatherservice.dto.response.ResponseErrorDto;
import com.globant.openweatherservice.exception.InvalidDataException;
import com.globant.openweatherservice.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.globant.openweatherservice.utils.Constants.CLIENT_ERROR;
import static com.globant.openweatherservice.utils.Constants.HANDLE_ERRORS;
import static com.globant.openweatherservice.utils.Constants.INVALID_DATA;
import static com.globant.openweatherservice.utils.Constants.VALIDATION_FAILED;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @Autowired
    WeatherService weatherService;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest){
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorDto errorDto = new ErrorDto(HANDLE_ERRORS, details);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(InvalidDataException invalidDataException, WebRequest webRequest){
        List<String> details = new ArrayList<>();
        details.add(invalidDataException.getLocalizedMessage());
        ErrorDto errorDto = new ErrorDto(INVALID_DATA, details);
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<Object> handleHttpClientException(HttpClientErrorException httpClientErrorException, WebRequest webRequest){
        List<String> details = new ArrayList<>();;
        ResponseErrorDto responseErrorDto = httpClientErrorException.getResponseBodyAs(ResponseErrorDto.class);
        details.add(httpClientErrorException.getStatusCode().toString());
        details.add(responseErrorDto.getMessage());
        ErrorDto errorDto = new ErrorDto(CLIENT_ERROR, details);
        weatherService.saveWeatherOperation("NA", responseErrorDto.getCod().toString(), responseErrorDto.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest){
        List<String> details = new ArrayList<>();
        for(ObjectError error: methodArgumentNotValidException.getBindingResult().getAllErrors()){
            details.add(error.getDefaultMessage());
        }
        ErrorDto errorDto = new ErrorDto(VALIDATION_FAILED, details);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
