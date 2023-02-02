package com.example.group.demoArtifact01.student;

/*import com.example.group.demoArtifact01.student.openweather.Weather;*/
import com.example.group.demoArtifact01.student.openweather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("weather")
public class CityController {

    private final CityService cityService;
    private final WeatherService weatherService;

    @Autowired
    public CityController( WeatherService weatherService, CityService cityService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
    }





    @PostMapping
    public void registerNewCity(@RequestBody City city){
        System.out.println("running controller method registerNewStudent");
        System.out.println("city to insert: " + city);
        cityService.addNewCity(city);
    }

    @PostMapping("/latitude/{latitude}/longitude/{longitude}")
    public void storeWeather(@PathVariable Double latitude, @PathVariable Double longitude){
        System.out.println("running controller method storeWeather");

        System.out.println("lat: " + latitude);
        System.out.println("lon: " + longitude);
        System.out.println("weather to insert: "  );
        /*Double latitude= 19.45105d;
        Double longitude= -99.125519d;*/
        Double lat= latitude;
        Double lon= longitude;
        weatherService.insertWeatherDetails( lat,  lon);
    }

    @PostMapping("/city/{cityName}")
    public void storeCity(@PathVariable String cityName){
        System.out.println("running controller method storeCity");

        System.out.println("cityName: " + cityName);

        /*weatherService.insertCityDetails( cityName);*/
    }


}
