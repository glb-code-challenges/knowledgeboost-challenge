package com.globant.weather.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class WeatherException extends Exception {
    private HttpStatus code;
    private List<String> errors;
}
