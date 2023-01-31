package com.globant.knowledgeboostchallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.knowledgeboostchallenge.dto.WeatherDTO;
import com.globant.knowledgeboostchallenge.service.KnowledgeboostChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class KnowledgeboostChallengeController {
    @Autowired
    KnowledgeboostChallengeService knowledgeboostChallengeService;

    @Operation(summary = "Get weather information by city name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the city",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WeatherDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    @GetMapping("/city/{cityName}")
    public WeatherDTO getByCity(@PathVariable String cityName) throws JsonProcessingException {

        return knowledgeboostChallengeService.getByCity(cityName);
    }
    @Operation(summary = "Get weather information by coordinates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the location",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WeatherDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Location not found",
                    content = @Content) })
    @GetMapping("/latitude/{lat}/longitude/{lon}")
    public WeatherDTO getByCoordinates(@PathVariable String lat,
                                       @PathVariable String lon) throws JsonProcessingException {
        return knowledgeboostChallengeService.getByCoordinates(lat, lon);
    }

}
