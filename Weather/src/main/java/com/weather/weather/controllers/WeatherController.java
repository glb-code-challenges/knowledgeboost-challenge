package com.weather.weather.controllers;

import com.weather.weather.services.WeatherServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherServiceI weatherServiceI;

    @GetMapping("/city/{cityName}")
    public Object getCityWeather(@PathVariable String cityName){
        return weatherServiceI.consultWeather(weatherServiceI.createRequestWeather(cityName));
    }

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public Object getLatitudeLongitudeWeather(@PathVariable String latitude, @PathVariable String longitude ){
        return weatherServiceI.consultWeather(weatherServiceI.createRequestWeather(latitude, longitude));
    }
}