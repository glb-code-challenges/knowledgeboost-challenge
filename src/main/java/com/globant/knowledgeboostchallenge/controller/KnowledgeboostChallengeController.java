package com.globant.knowledgeboostchallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.knowledgeboostchallenge.dto.WeatherDTO;
import com.globant.knowledgeboostchallenge.service.KnowledgeboostChallengeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class KnowledgeboostChallengeController {
    @Autowired
    KnowledgeboostChallengeService knowledgeboostChallengeService;

    @GetMapping("/city/{cityName}")
    public WeatherDTO getByCity(@PathVariable String cityName) throws JsonProcessingException {

        return knowledgeboostChallengeService.getByCity(cityName);
    }
    @GetMapping("/latitude/{lat}/longitude/{lon}")
    public WeatherDTO getByCoordinates(@PathVariable String lat,
                                       @PathVariable String lon) throws JsonProcessingException {
        return knowledgeboostChallengeService.getByCoordinates(lat, lon);
    }

}
