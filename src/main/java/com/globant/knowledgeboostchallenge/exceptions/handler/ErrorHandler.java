package com.globant.knowledgeboostchallenge.exceptions.handler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import com.globant.knowledgeboostchallenge.dao.models.ErrorDetailModel;
import com.globant.knowledgeboostchallenge.dao.models.ErrorService;
import com.globant.knowledgeboostchallenge.dao.models.ResponseDataModel;
import com.globant.knowledgeboostchallenge.services.WeatherService;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;


@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static final String BAD_REQUEST_FAVOR_VERIFICAR_DATOS = "bad request, favor verificar datos";

    private final WeatherService weatherService;

    public ErrorHandler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetailModel> constraintException(ConstraintViolationException constraintViolationException) {
        WeatherInfoResponse infoResponse= new WeatherInfoResponse();
        infoResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        infoResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        infoResponse.setCityName("No consulto ningun clima =>"+constraintViolationException.getMessage());
        infoResponse.setErrorCause(constraintViolationException.getMessage());
        weatherService.saveWeatherInfoResponse(infoResponse);
        ErrorDetailModel error = new ErrorDetailModel(new Date(),constraintViolationException.getMessage(), BAD_REQUEST_FAVOR_VERIFICAR_DATOS,HttpStatus.BAD_REQUEST.toString() , "ConstraintViolationException");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorDetailModel> httpClientErrorException(HttpClientErrorException clientErrorException) throws JsonProcessingException {
        log.error("HTTP_RESPONSE_ERROR", clientErrorException.getRawStatusCode());
        log.error("RESPONSE_ERROR", clientErrorException.getResponseBodyAsString());

        String responseBody = clientErrorException.getResponseBodyAsString();
        ErrorService errorService = new ObjectMapper().readValue(responseBody, ErrorService.class);
        ErrorDetailModel error = new ErrorDetailModel(new Date(), errorService.getMessage(), BAD_REQUEST_FAVOR_VERIFICAR_DATOS, errorService.getCod(), "HttpClientErrorException");
        WeatherInfoResponse infoResponse= new WeatherInfoResponse();
        infoResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        infoResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        infoResponse.setCityName("No consulto ningun clima =>"+responseBody);
        infoResponse.setErrorCause(BAD_REQUEST_FAVOR_VERIFICAR_DATOS);
        weatherService.saveWeatherInfoResponse(infoResponse);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }





}