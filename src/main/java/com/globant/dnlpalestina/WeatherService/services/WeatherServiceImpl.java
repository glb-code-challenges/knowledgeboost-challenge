package com.globant.dnlpalestina.WeatherService.services;

import com.globant.dnlpalestina.WeatherService.models.entities.Coord;
import com.globant.dnlpalestina.WeatherService.models.entities.WeatherDetails;
import com.globant.dnlpalestina.WeatherService.models.entities.WeatherEntity;
import com.globant.dnlpalestina.WeatherService.repositories.WeatherRepository;
import com.globant.dnlpalestina.WeatherService.util.RestTemplateResponseErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService{

    private static final Logger Log = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WeatherRepository weatherRepository;

    @Value("${openweathermap.org.appId}")
    private String appId;

    @Value("${openweathermap.org.getWeatherDetailsByCityNameURL}")
    private String weatherDetailsByCityNameURL;

    @Value("${openweathermap.org.getWeatherDetailsByLatLongURL}")
    private String weatherDetailsByLatLongURL;

    @Override
    public Iterable<WeatherEntity> findAll() {
        Iterable<WeatherEntity> weather = new Iterable<WeatherEntity>() {
            @Override
            public Iterator<WeatherEntity> iterator() {
                return null;
            }
        };
        try{
            weather = weatherRepository.findAll();
        } catch (ResourceNotFoundException e) {
            Log.error("Failed to get WeatherEntities",e);
        }
        return weather;
    }

    @Override
    public WeatherDetails getWeatherDetailsByCityName(String cityName) {
        Log.info("getWeatherDetailsByCityName() using openweathermap.org.appId:"+appId);

        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("cityName", cityName);
        pathVariables.put("appId", appId);
        WeatherDetails weatherDetails = new WeatherDetails();
        try{
            weatherDetails = restTemplate.getForObject(
                weatherDetailsByCityNameURL,
                WeatherDetails.class,
                pathVariables);
        } catch (HttpClientErrorException httpClientErrorException) {
            Log.info("handling Errors!!!");
            Log.info(httpClientErrorException.getMessage());
            weatherDetails.setCod(httpClientErrorException.getStatusCode().value());
            weatherDetails.setName(cityName);
            weatherDetails.setCoord(new Coord());
            weatherDetails.setMessage(httpClientErrorException.getMessage());
        }
        saveWeatherEntity(weatherDetails);
        return weatherDetails;
    }

    @Override
    public WeatherDetails getWeatherDetailsByLatLong(String lat, String lon) {
        Log.info("getWeatherDetailsByLatLong() using openweathermap.org.appId:"+appId);

        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("lat", lat);
        pathVariables.put("lon", lon);
        pathVariables.put("appId", appId);
        WeatherDetails weatherDetails = new WeatherDetails();
        try {
            weatherDetails = restTemplate.getForObject(
                    weatherDetailsByLatLongURL,
                    WeatherDetails.class,
                    pathVariables);
        } catch (HttpClientErrorException httpClientErrorException){
            Log.info("handling Errors!!!");
            Log.info(httpClientErrorException.getMessage());
            weatherDetails.setCod(httpClientErrorException.getStatusCode().value());
            weatherDetails.setCoord(new Coord(Double.parseDouble(lat), Double.parseDouble(lon)));
            weatherDetails.setMessage(httpClientErrorException.getMessage());
        }
        saveWeatherEntity(weatherDetails);
        return weatherDetails;
    }

    private void saveWeatherEntity(WeatherDetails weatherDetails){
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCreatedAt(new Date());
        weatherEntity.setResponseCode(weatherDetails.getCod());
        weatherEntity.setCityName(weatherDetails.getName());
        weatherEntity.setCoords(weatherDetails.getCoord().toString());
        weatherEntity.setRootCause(weatherDetails.getMessage());
        weatherRepository.save(weatherEntity);
    }
}
