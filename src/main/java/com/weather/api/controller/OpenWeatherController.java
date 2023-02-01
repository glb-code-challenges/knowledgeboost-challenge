package com.weather.api.controller;

import com.weather.api.client.response.Coord;
import com.weather.api.client.response.WeatherResponse;
import com.weather.api.record.ResponseRecord;
import com.weather.api.service.WeatherService;
import com.weather.api.util.ResponseMessage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Slf4j
public class OpenWeatherController {
    private final WeatherService weatherService;

    private static final String CITY_OK = "City was found";
    private static final String LATITUDE_AND_LONGITUDE_OK  = "Latitude and longitude were found";

    @GetMapping(value = "/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseRecord<WeatherResponse>> getWeatherByCityName(
            @PathVariable @Valid @NotBlank String cityName) {
        log.info("Looking for the city: {}", cityName);

        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.SUCCESS, CITY_OK, null,
                weatherService.getWeatherByCityName(cityName)),  HttpStatus.OK);
    }

    @GetMapping(value = "/latitude/{lat}/longitude/{lon}")
    public ResponseEntity<ResponseRecord<WeatherResponse>> getWeatherByLatitudeAndLongitude(
            @Valid Coord coord) {
        log.info("Looking weather by latitude: {} and longitude: {}", coord.getLat(), coord.getLon());

        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.SUCCESS, LATITUDE_AND_LONGITUDE_OK, null,
                weatherService.getWeatherByLatitudeAndLongitude(coord.getLat(), coord.getLon())),  HttpStatus.OK);
    }
}
