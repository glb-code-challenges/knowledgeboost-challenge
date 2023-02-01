package com.weather.api.controller;

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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Weather service", description = "This service call the Open Weather API in order to get weather data by city name or latitude/longitud.")
@Validated
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
            @PathVariable
            @Parameter(name = "cityName", description = "City name", schema = @Schema(type = "string"), required = true, in = ParameterIn.PATH, example = "Mexico city")
            @Valid @NotBlank String cityName) {
        log.info("Looking for the city: {}", cityName);

        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.SUCCESS, CITY_OK, null,
                weatherService.getWeatherByCityName(cityName)),  HttpStatus.OK);
    }

    @Operation(summary = "Get weather by latitude and longitude")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = LATITUDE_AND_LONGITUDE_OK),
            @ApiResponse(responseCode = "404", description = Constants.LATITUDE_AND_LONGITUDE_NOT_FOUND)})
    @GetMapping(value = "/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<ResponseRecord<WeatherResponse>> getWeatherByLatitudeAndLongitude(
            @PathVariable
            @Parameter(name = "latitude", description = "Latitude", schema = @Schema(type = "number"), required = true, in = ParameterIn.PATH, example = "19.4285")
            @Valid @DecimalMin("-90.0") @DecimalMax("90.0") @Digits(integer = 2, fraction = 6) Double latitude,

            @PathVariable
            @Parameter(name = "longitude", description = "Longitude", schema = @Schema(type = "number"), required = true, in = ParameterIn.PATH, example = "-99.1277")
            @Valid @DecimalMin("-180.0") @DecimalMax("180.0") @Digits(integer=3, fraction=6) Double longitude
        ) {
        log.info("Looking weather by latitude: {} and longitude: {}", latitude, longitude);

        return new ResponseEntity<>(new ResponseRecord<>(ResponseMessage.SUCCESS, LATITUDE_AND_LONGITUDE_OK, null,
                weatherService.getWeatherByLatitudeAndLongitude(latitude, longitude)),  HttpStatus.OK);
    }
}
