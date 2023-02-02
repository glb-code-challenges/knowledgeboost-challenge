package com.marco.estrada.weather.WeatherOrchestrator.exceptions;


import com.marco.estrada.weather.WeatherOrchestrator.dto.ErrorDetail;
import com.marco.estrada.weather.WeatherOrchestrator.dto.ErrorResponse;
import com.marco.estrada.weather.WeatherOrchestrator.dto.Request;
import com.marco.estrada.weather.WeatherOrchestrator.service.PublishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

  private final PublishService publishService;

  @ExceptionHandler(ClientException.class)
  @ResponseBody
  public ErrorResponse manageClientException(ClientException exception) {
    publishService.sentMessagePersist(
        Request.builder()
            .date(new Date())
            .cityName(exception.getCountry())
            .statusCode(exception.getErrorResponse().getCode())
            .errorDetail(
                ErrorDetail.builder()
                    .description(exception.getErrorResponse().getDescription())
                    .build())
            .build());
    log.error("Error executing client ", exception);
    return exception.getErrorResponse();
  }
}
