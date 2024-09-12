package com.marco.estrada.weather.WeatherOrchestrator.controller;


import com.marco.estrada.weather.WeatherOrchestrator.dto.Position;
import com.marco.estrada.weather.WeatherOrchestrator.dto.WeatherResp;
import com.marco.estrada.weather.WeatherOrchestrator.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("weather")
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService service;

    @GetMapping("city/{cityName}")
    public WeatherResp getCityWeather (@PathVariable String cityName) {
        return service.getCityWeather(cityName);
    }

    @GetMapping("latitude/{latitude}/longitude/{longitude}")
    public WeatherResp getPositionWeather(
            @PathVariable double latitude, @PathVariable double longitude) {
        return service.getPositionWeather(
                Position.builder()
                        .latitude(latitude)
                        .longitude(longitude)
                        .build());
    }
}
