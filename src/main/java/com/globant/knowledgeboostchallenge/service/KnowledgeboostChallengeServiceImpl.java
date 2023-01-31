package com.globant.knowledgeboostchallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.knowledgeboostchallenge.dto.WeatherDTO;
import com.globant.knowledgeboostchallenge.entity.Weather;
import com.globant.knowledgeboostchallenge.repository.WeatherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class KnowledgeboostChallengeServiceImpl implements KnowledgeboostChallengeService{

    @Value("${apikey}")
    private String apikey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WeatherRepository weatherRepository;

    private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private final String BY_CITY_NAME = "BY_CITY_NAME";
    private final String BY_COORDINATES = "BY_COORDINATES";
    @Override
    public WeatherDTO getByCity(String cityName) throws JsonProcessingException {
        String url = WEATHER_URL + "q=" + cityName + "&appid=" + apikey;
        return getWeatherDTO(url, BY_CITY_NAME);
    }

    @Override
    public WeatherDTO getByCoordinates(String lat, String lon) throws JsonProcessingException {
        String url = WEATHER_URL + "lat=" + lat + "&lon=" + lon + "&appid=" + apikey;
        return getWeatherDTO(url, BY_COORDINATES);
    }

    private WeatherDTO getWeatherDTO(String url, String type) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WeatherDTO weather = null;
        try {
            ResponseEntity<String> response
                    = restTemplate.getForEntity(url, String.class);
            weather = mapper.readValue(response.getBody(), WeatherDTO.class);
        } catch(Exception e) {
            String jsonMessage = e.getMessage().substring( e.getMessage().indexOf("{"));
            weather = mapper.readValue(jsonMessage, WeatherDTO.class);
            LocalDateTime now = LocalDateTime.now();
            weather.setDt(Long.toString(now.toEpochSecond(ZoneOffset.of("-06:00"))));
        }
        weather.setType(type);
        weatherRepository.save(modelMapper.map(weather, Weather.class));
        return weather;
    }
}
