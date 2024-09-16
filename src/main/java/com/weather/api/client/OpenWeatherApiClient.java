package com.weather.api.client;

import com.weather.api.client.response.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherApiClient", url = "${openweather.url}")
public interface OpenWeatherApiClient {
    @GetMapping("/weather")
    WeatherResponse getWeatherByCityName(
            @RequestParam(value = "q") String cityName,
            @RequestParam(value = "appid") String appid);

    @GetMapping("/weather")
    WeatherResponse getWeatherByLatitudeAndLongitude(
            @RequestParam(value = "lat") Double latitude,
            @RequestParam(value = "lon") Double longitude,
            @RequestParam(value = "appid") String appid);
}
