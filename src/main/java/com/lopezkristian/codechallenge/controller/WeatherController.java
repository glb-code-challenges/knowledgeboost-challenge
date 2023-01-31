package com.lopezkristian.codechallenge.controller;

import com.lopezkristian.codechallenge.model.LogWeather;
import com.lopezkristian.codechallenge.services.IWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather/")
@Slf4j
public class WeatherController {

    IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("city/")
    public ResponseEntity<?> cityNameEmpty() {
        final String ERROR = "It is required to enter the name of a city or coordinates.";
        log.error(ERROR);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ERROR);
    }

    /**
     * @param cityName City Name
     * @return response
     */
    @GetMapping("city/{cityName}")
    public ResponseEntity<?> getCity(@PathVariable String cityName) {
        LogWeather logWeather = weatherService.getCity(cityName);
        return new ResponseEntity<>(logWeather, HttpStatus.OK);
    }

    /**
     * @param latitude  latitude
     * @param longitude longitude
     * @return response
     */
    @GetMapping("latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<?> getWeatherLatitudeLongitude(@PathVariable String latitude, @PathVariable String longitude) {
        LogWeather logWeather = weatherService.getWeatherLatitudeLongitude(latitude, longitude);
        return new ResponseEntity<>(logWeather, HttpStatus.OK);
    }

}
