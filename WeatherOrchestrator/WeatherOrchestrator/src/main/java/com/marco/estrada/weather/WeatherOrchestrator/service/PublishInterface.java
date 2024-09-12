package com.marco.estrada.weather.WeatherOrchestrator.service;

import com.marco.estrada.weather.WeatherOrchestrator.dto.Request;

public interface PublishInterface {

  void sentMessagePersist(Request request);
}
