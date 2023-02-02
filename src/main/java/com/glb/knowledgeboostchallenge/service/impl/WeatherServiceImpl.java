package com.glb.knowledgeboostchallenge.service.impl;

import com.glb.knowledgeboostchallenge.api.OpenWeatherClient;
import com.glb.knowledgeboostchallenge.dto.WeatherRequest;
import com.glb.knowledgeboostchallenge.api.model.OpenWeatherResponse;
import com.glb.knowledgeboostchallenge.dto.WeatherResponse;
import com.glb.knowledgeboostchallenge.exception.BaseWeatherException;
import com.glb.knowledgeboostchallenge.exception.DataBaseException;
import com.glb.knowledgeboostchallenge.model.ResponseLog;
import com.glb.knowledgeboostchallenge.repository.WeatherRepository;
import com.glb.knowledgeboostchallenge.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.glb.knowledgeboostchallenge.utils.Constants.OPEN_WEATHER_BY_CITY;
import static com.glb.knowledgeboostchallenge.utils.Constants.OPEN_WEATHER_BY_COORDINATES;
import static com.glb.knowledgeboostchallenge.utils.WeatherUtils.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public WeatherResponse getWeatherByCity(WeatherRequest request) throws BaseWeatherException {
        String endpoint = String.format(OPEN_WEATHER_BY_CITY, request.getCityName(), request.getApiKey());
        return getWeather(endpoint);
    }

    @Override
    public WeatherResponse getWeatherByCoordinates(WeatherRequest request)
        throws BaseWeatherException {
        String endpoint = String.format(
            OPEN_WEATHER_BY_COORDINATES,
            request.getLatitude(),
            request.getLongitude(),
            request.getApiKey());
        return getWeather(endpoint);
    }

    public WeatherResponse getWeather(String endpoint) throws BaseWeatherException {
        OpenWeatherResponse openWeatherResponse = null;
        String response = OpenWeatherClient.makeRequest(endpoint);
        openWeatherResponse = getResponseApi(response);
        ResponseLog log = createResponseLog(openWeatherResponse, response);
        try {
            weatherRepository.save(log);
        } catch (Exception ex) {
            throw new DataBaseException(ex.getMessage());
        }
        return convertWeatherResponse(openWeatherResponse);
    }

}
