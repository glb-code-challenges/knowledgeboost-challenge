package com.globant.weather.controller;

import com.globant.weather.dto.ErrorResponse;
import com.globant.weather.dto.WeatherResponse;
import com.globant.weather.exeption.WeatherException;
import com.globant.weather.service.IWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/v1/weather")
@Validated
public class WeatherController {

    private final IWeatherService weatherService;

    @Autowired
    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Get weather by city name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the weather", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WeatherResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid cityName", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "city not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/city/{cityName}")
    public WeatherResponse getByCity(@PathVariable("cityName") @Pattern(regexp = "^[a-zA-Z ,]*$", message = "it's a required value without numbers or special characters") String cityName) throws WeatherException {
        return this.weatherService.getByCityName(cityName);
    }

    @Operation(summary = "Get weather by latitude & longitude.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the weather using latitude & longitude", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid latitude or longitude", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "coordinates not found", content =  { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal error", content =  { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
    })
    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public WeatherResponse getByCoordinates(@PathVariable("latitude") @Pattern(regexp = "^-?([0-8]?[0-9]|90)(\\.[0-9]{1,10})$", message = "it's a required value between -90.0 and 90.0") String latitude, @PathVariable("longitude") @Pattern(regexp = "^-?([0-9]{1,2}|1[0-7][0-9]|180)(\\.[0-9]{1,10})$", message = "it's a required value between -180.0 and 180.0") String longitude) throws WeatherException {
        return this.weatherService.getByCoordinates(latitude, longitude);
    }
}
