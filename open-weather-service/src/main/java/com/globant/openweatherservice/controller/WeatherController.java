package com.globant.openweatherservice.controller;

import com.globant.openweatherservice.dto.ResponseDto;
import com.globant.openweatherservice.service.WeatherService;
import com.globant.openweatherservice.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weather/")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Operation(summary = "Request City Weather and save on database")
    @PostMapping(value = "/city/{city}")
    public ResponseEntity<ResponseDto<String>> cityWeatherRequest(@PathVariable("city") String city) {
        //RequestImageDto requestImageDto = new RequestImageDto(request.getImage(), request.getImageTitle(), request.getImageDescription(), request.getTargetFolderName(), request.getEmails());
        //log.info("Receiving request with following data {}", requestImageDto);
        ResponseDto responseDto = new ResponseDto(Constants.ResponseConstant.SUCCESS.getDescription(), weatherService.cityWeatherRequest(city));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Request weather by latitude and longitude, then save on database")
    @PostMapping(value = "/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<ResponseDto<String>> latitudeLongitudeWeatherRequest(@PathVariable("latitude") Float latitude, @PathVariable("longitude") Float longitude) {
        //RequestImageDto requestImageDto = new RequestImageDto(request.getImage(), request.getImageTitle(), request.getImageDescription(), request.getTargetFolderName(), request.getEmails());
        //log.info("Receiving request with following data {}", requestImageDto);
        ResponseDto responseDto = new ResponseDto(Constants.ResponseConstant.SUCCESS.getDescription(), weatherService.latitudeLongitudeWeatherRequest(latitude, longitude));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
