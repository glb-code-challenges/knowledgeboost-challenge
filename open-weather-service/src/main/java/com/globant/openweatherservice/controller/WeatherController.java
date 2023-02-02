package com.globant.openweatherservice.controller;

import com.globant.openweatherservice.dto.ResponseDto;
import com.globant.openweatherservice.dto.response.WeatherResponseDto;
import com.globant.openweatherservice.service.WeatherService;
import com.globant.openweatherservice.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/weather/")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Operation(summary = "Request City Weather and save on database")
    @PostMapping(value = "/city/{city}")
    public ResponseEntity<ResponseDto<WeatherResponseDto>> cityWeatherRequest(@PathVariable ("city") @Size(min = 3, max = 15, message = "The length of the city must be between 3 and 15 characters") String city) {
        log.info("Receiving request with following city: {}", city);
        ResponseDto responseDto = new ResponseDto(Constants.ResponseConstant.SUCCESS.getDescription(), weatherService.cityWeatherRequest(city));
        return new ResponseEntity<ResponseDto<WeatherResponseDto>>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Request weather by latitude and longitude, then save on database")
    @PostMapping(value = "/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<ResponseDto<WeatherResponseDto>> latitudeLongitudeWeatherRequest(@PathVariable("latitude") @Min(value = -90, message = "Latitude must be above -90") @Max(value = 90, message = "Latitude must be below 90") Float latitude ,
                                                                               @PathVariable("longitude") @Min(value = -180, message = "Longitude must be above -180") @Max(value = 180, message = "Longitude must be below 180") Float longitude) {
        log.info("Receiving request with following data: latitude {}, longitude {}", latitude, longitude);
        ResponseDto responseDto = new ResponseDto(Constants.ResponseConstant.SUCCESS.getDescription(), weatherService.latitudeLongitudeWeatherRequest(latitude, longitude));
        return new ResponseEntity<ResponseDto<WeatherResponseDto>>(responseDto, HttpStatus.OK);
    }
}
