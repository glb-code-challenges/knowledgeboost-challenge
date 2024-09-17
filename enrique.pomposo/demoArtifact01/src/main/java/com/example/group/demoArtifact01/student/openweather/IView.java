package com.example.group.demoArtifact01.student.openweather;

import org.springframework.stereotype.Component;


public interface IView {

    void onWeatherApiSuccess(WeatherModel weatherData);

    void onWeatherApiFailure(String message);


}
