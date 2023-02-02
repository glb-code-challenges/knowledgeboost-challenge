package com.marco.estrada.weather.WeatherPersistance.controller;

import com.marco.estrada.weather.WeatherPersistance.dto.Request;
import com.marco.estrada.weather.WeatherPersistance.service.StoreDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Profile("aws")
@RequiredArgsConstructor
@RestController
public class WorkerController {

  private final StoreDataService service;

  @PostMapping("startProcess")
  public void startProcessData(@RequestBody Request request) {
    log.info("Request received {}" , request);
    service.storeRequestData(request);
  }
}
