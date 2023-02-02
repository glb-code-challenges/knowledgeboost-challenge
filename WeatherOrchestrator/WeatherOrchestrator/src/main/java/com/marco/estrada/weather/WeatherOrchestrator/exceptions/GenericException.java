package com.marco.estrada.weather.WeatherOrchestrator.exceptions;

import com.marco.estrada.weather.WeatherOrchestrator.dto.ErrorResponse;
import feign.FeignException;

public class GenericException extends RuntimeException{


  public ErrorResponse errorResponse;


  private final String ERROR_TYPE = "Internal server error";

  public GenericException(Exception exception) {
    this.errorResponse = ErrorResponse.builder()
        .code(500)
        .error(ERROR_TYPE)
        .description(exception.getMessage())
        .build();
  }
}
