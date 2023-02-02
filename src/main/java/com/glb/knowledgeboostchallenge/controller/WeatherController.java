package com.glb.knowledgeboostchallenge.controller;

import javax.validation.constraints.NotBlank;

import com.glb.knowledgeboostchallenge.dto.WeatherRequest;
import com.glb.knowledgeboostchallenge.dto.WeatherResponse;
import com.glb.knowledgeboostchallenge.exception.BaseWeatherException;
import com.glb.knowledgeboostchallenge.service.impl.WeatherServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.glb.knowledgeboostchallenge.utils.Constants.*;

@RestController
@RequestMapping(WEATHER_API)
@Validated
@ApiOperation("Weather API")
public class WeatherController {

    private final WeatherServiceImpl weatherService;

    public WeatherController(WeatherServiceImpl service) {
        this.weatherService = service;
    }

    @ApiOperation(value = "Get weather by city", notes = "Returns a the weather information of today")
    @ApiResponses(value = {
        @ApiResponse(code=200, message="OK", response=WeatherResponse.class),
        @ApiResponse(code=500, message=MESSAGE_SERVER_ERROR),
        @ApiResponse(code=404, message=MESSAGE_CITY_NOT_FOUND),
        @ApiResponse(code=401, message=MESSAGE_AUTH_MISSING),
        @ApiResponse(code=503, message=MESSAGE_DATABASE_UNAVAILABLE )
    })
    @GetMapping(value = GET_BY_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherResponse> getWeatherByCity(
        @ApiParam(name = "cityName", value = "City to get its weather", example = "London")
        @PathVariable("cityName") @NotBlank String cityName,
        @ApiParam(hidden = true) @RequestHeader(value = "apiKey") String apiKey)
        throws BaseWeatherException {
        WeatherResponse res = weatherService.getWeatherByCity(new WeatherRequest(apiKey,cityName));
        return new ResponseEntity<>(res, HttpStatus.resolve(res.getCode()));
    }

    @ApiOperation(value = "Get weather by coordinates (latitude and longitude)", notes = "Returns a the weather information of today")
    @ApiResponses(value = {
        @ApiResponse(code=200, message="OK", response=WeatherResponse.class),
        @ApiResponse(code=500, message=MESSAGE_SERVER_ERROR),
        @ApiResponse(code=404, message=MESSAGE_CITY_NOT_FOUND),
        @ApiResponse(code=401, message=MESSAGE_AUTH_MISSING),
        @ApiResponse(code=503, message=MESSAGE_DATABASE_UNAVAILABLE)
    })
    @GetMapping(value = GET_BY_COORDINATES, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherResponse> getWeatherByCoordinates(
        @ApiParam(name = "latitude", value = "City geo location, latitude", example = "18.0")
        @PathVariable("latitude") @NotBlank String latitude,
        @ApiParam(name = "longitude", value = "City geo location, longitude", example = "-92.6667")
        @PathVariable("longitude") @NotBlank String longitude,
        @ApiParam(hidden = true) @RequestHeader(value = "apiKey") String apiKey)
        throws BaseWeatherException {
        WeatherResponse res =  weatherService.getWeatherByCoordinates(new WeatherRequest(apiKey, latitude, longitude));
        return new ResponseEntity<>(res, HttpStatus.resolve(res.getCode()));
    }

}
