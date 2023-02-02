package com.globant.dnlpalestina.WeatherService.controllers;

import com.globant.dnlpalestina.WeatherService.models.entities.WeatherDetails;
import com.globant.dnlpalestina.WeatherService.models.entities.WeatherEntity;
import com.globant.dnlpalestina.WeatherService.services.WeatherService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class weatherController {

    private static final Logger Log = LoggerFactory.getLogger(weatherController.class);

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weathers")
    private Iterable<WeatherEntity> getAllWeathers(){
        Log.info("Retrieving all weather info in DB");
        Iterable<WeatherEntity> weather = weatherService.findAll();
        return weather;
    }

    @GetMapping("/weather/city/{cityName}")
    public ResponseEntity<WeatherDetails> getWeatherDetailsByCityName(@PathVariable String cityName){
        Log.info("Retrieving Weather Details By City Name");
        WeatherDetails weatherDetails = weatherService.getWeatherDetailsByCityName(cityName);

        if (weatherDetails.getCod() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            Log.info("httpResponse is 5xxServerError!!!");
            return ResponseEntity.internalServerError().body(weatherDetails);
        } else if (weatherDetails.getCod() == HttpStatus.BAD_REQUEST.value()) {
            Log.info("httpResponse is 4xxClientError!!!");
            return ResponseEntity.badRequest().body(weatherDetails);
        }else if (weatherDetails.getCod() == HttpStatus.NOT_FOUND.value()) {
            Log.info("httpResponse is NOT_FOUND ERROR!!!");
            return ResponseEntity.status(weatherDetails.getCod()).body(weatherDetails);
        }

        return ResponseEntity.ok().body(weatherDetails);
    }

    @GetMapping("/weather/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<WeatherDetails> getWeatherDetailsByLatLong(@PathVariable String latitude, @PathVariable String longitude){
        Log.info("Retrieving Weather Details By Latitude and Longitude");
        WeatherDetails weatherDetails = weatherService.getWeatherDetailsByLatLong(latitude, longitude);

        if (weatherDetails.getCod() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            Log.info("httpResponse is 5xxServerError!!!");
            return ResponseEntity.internalServerError().body(weatherDetails);
        } else if (weatherDetails.getCod() == HttpStatus.BAD_REQUEST.value()) {
            Log.info("httpResponse is 4xxClientError!!!");
            return ResponseEntity.badRequest().body(weatherDetails);
        }else if (weatherDetails.getCod() == HttpStatus.NOT_FOUND.value()) {
            Log.info("httpResponse is NOT_FOUND ERROR!!!");
            return ResponseEntity.status(weatherDetails.getCod()).body(weatherDetails);
        }

        return ResponseEntity.ok().body(weatherDetails);
    }



}
