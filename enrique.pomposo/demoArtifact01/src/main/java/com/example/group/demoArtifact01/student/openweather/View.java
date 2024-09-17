package com.example.group.demoArtifact01.student.openweather;

import org.springframework.stereotype.Component;

@Component
public class View implements IView{
    @Override
    public void onWeatherApiSuccess(WeatherModel weatherData) {

    }

    @Override
    public void onWeatherApiFailure(String message) {

    }
}
