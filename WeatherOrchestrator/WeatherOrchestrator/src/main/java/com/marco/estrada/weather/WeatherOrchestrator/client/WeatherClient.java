package com.marco.estrada.weather.WeatherOrchestrator.client;


import com.marco.estrada.weather.WeatherOrchestrator.dto.WeatherResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.openweathermap.org", name = "weatherClient")
public interface WeatherClient {

    @GetMapping("/data/2.5/weather")
    WeatherResp getWeather(
            @RequestParam("appid") String appid,
            @RequestParam("lon") double longitude,
            @RequestParam("lat") double latitude,
            @RequestParam("q") String cityName);
}
