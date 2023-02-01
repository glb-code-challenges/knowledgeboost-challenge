package com.weather.api.controller;

import com.weather.api.request.RequestCityName;
import com.weather.api.client.response.Coord;
import com.weather.api.client.response.WeatherResponse;
import com.weather.api.record.ResponseRecord;
import com.weather.api.service.WeatherService;
import com.weather.api.util.Constants;
import com.weather.api.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Weather service", description = "This service call the Open Weather API in order to get weather data by city name or latitude/longitud.")
public class OpenWeatherController {
    private final WeatherService weatherService;

    private static final String CITY_OK = "City was found";
    private static final String LATITUDE_AND_LONGITUDE_OK  = "Latitude and longitude were found";

    @Operation(summary = "Get weather by city name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = CITY_OK),
            @ApiResponse(responseCode = "404", description = Constants.CITY_NOT_FOUND)})
    @GetMapping(value = "/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseRecord<WeatherResponse>> getWeatherByCityName(
            @Parameter(name = "cityName", description = "City name", schema = @Schema(type = "string"), required = true, in = ParameterIn.PATH, example = "Mexico city")
            @Valid RequestCityName requestCityName) {
        log.info("Looking for the city: {}", requestCityName.getCityName());

        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.SUCCESS, CITY_OK, null,
                weatherService.getWeatherByCityName(requestCityName.getCityName())),  HttpStatus.OK);
    }

    @Operation(summary = "Get weather by latitude and longitude")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = LATITUDE_AND_LONGITUDE_OK),
            @ApiResponse(responseCode = "404", description = Constants.LATITUDE_AND_LONGITUDE_NOT_FOUND)})
    @GetMapping(value = "/latitude/{lat}/longitude/{lon}")
    public ResponseEntity<ResponseRecord<WeatherResponse>> getWeatherByLatitudeAndLongitude(

            @Valid Coord coord) {
        log.info("Looking weather by latitude: {} and longitude: {}", coord.getLat(), coord.getLon());

        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.SUCCESS, LATITUDE_AND_LONGITUDE_OK, null,
                weatherService.getWeatherByLatitudeAndLongitude(coord.getLat(), coord.getLon())),  HttpStatus.OK);
    }
}
