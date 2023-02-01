package com.globant.weather.utility;

import com.globant.weather.dto.ApiError;
import com.globant.weather.entity.Weather;
import com.globant.weather.exeption.WeatherException;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Utils {

    public static Weather getWeatherEntityFromApi(HttpClientErrorException httpClientErrorException) {
        Gson gson = new Gson();
        String body = httpClientErrorException.getResponseBodyAsString();
        ApiError apiError = gson.fromJson(body, ApiError.class);
        return new Weather(httpClientErrorException.getStatusCode().value(), apiError.getCod() + " - " + apiError.getMessage(), "", LocalDateTime.now());
    }

    public static void Error(HttpClientErrorException httpClientErrorException) throws WeatherException {
        Gson gson = new Gson();
        String body = httpClientErrorException.getResponseBodyAsString();
        ApiError apiError = gson.fromJson(body, ApiError.class);
        throw new WeatherException(httpClientErrorException.getStatusCode(), Arrays.asList(apiError.getCod() + " - " + apiError.getMessage()));
    }

    public static void Error(Exception exception) throws WeatherException {
        throw new WeatherException(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList("Internal Server Error"));
    }
}
