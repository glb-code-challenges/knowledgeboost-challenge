package com.marco.estrada.weather.WeatherOrchestrator.exceptions;

import com.marco.estrada.weather.WeatherOrchestrator.dto.ErrorResponse;
import feign.FeignException;
import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{


  public ErrorResponse errorResponse;

  private final String country;

  private final String ERROR_TYPE = "Error sending request to weather api";

  public ClientException(FeignException feignException, String cityName) {
    this.errorResponse = ErrorResponse.builder()
        .code(feignException.status())
        .error(ERROR_TYPE)
        .description(feignException.getMessage())
        .build();
    this.country = cityName;
  }

}
