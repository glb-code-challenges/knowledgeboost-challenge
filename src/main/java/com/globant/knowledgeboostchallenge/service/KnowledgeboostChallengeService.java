package com.globant.knowledgeboostchallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.knowledgeboostchallenge.dto.WeatherDTO;

public interface KnowledgeboostChallengeService {
    public WeatherDTO getByCity(String cityName) throws JsonProcessingException;
    public WeatherDTO getByCoordinates(String lat, String lon) throws JsonProcessingException;
}
