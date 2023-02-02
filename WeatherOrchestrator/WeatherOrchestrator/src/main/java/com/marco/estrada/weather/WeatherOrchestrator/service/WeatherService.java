package com.marco.estrada.weather.WeatherOrchestrator.service;

import com.marco.estrada.weather.WeatherOrchestrator.client.WeatherClient;
import com.marco.estrada.weather.WeatherOrchestrator.dto.Position;
import com.marco.estrada.weather.WeatherOrchestrator.dto.Request;
import com.marco.estrada.weather.WeatherOrchestrator.dto.WeatherResp;
import com.marco.estrada.weather.WeatherOrchestrator.exceptions.ClientException;
import com.marco.estrada.weather.WeatherOrchestrator.exceptions.GenericException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Callable;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${client.key}")
    String apiKey;

    private final WeatherClient client;

    private final PublishInterface publishServiceRabbit;

    public WeatherResp getCityWeather(String cityName){
        log.debug("Executing client");
        WeatherResp weatherResp = executeRequest(
            () -> client.getWeather(
                apiKey, 0, 0, cityName),
            cityName);
        publishServiceRabbit.sentMessagePersist(
            Request.builder()
                .statusCode(200)
                .date(new Date())
                .cityName(weatherResp.getName())
            .build());
        return weatherResp;
    }


    public WeatherResp getPositionWeather(Position position) {
        WeatherResp weatherResp = executeRequest(
            () -> client.getWeather(
                apiKey,
                position.getLongitude(),
                position.getLatitude(), ""), "");
        publishServiceRabbit.sentMessagePersist(
            Request.builder()
                .statusCode(200)
                .date(new Date())
                .cityName(weatherResp.getName())
                .build());
        return weatherResp;
    }


    private WeatherResp executeRequest(Callable<WeatherResp> callable, String name) {
        try {
            return callable.call();
        }
        catch (FeignException feignException) {
            log.error("Error executing request.", feignException);
            throw new ClientException(feignException, name);
        }
        catch (Exception exc) {
            log.error("Error executing callable", exc);
            throw new GenericException(exc);
        }
    }

}
