package com.globant.knowledgeboostchallenge.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class KnowledgeboostChallengeController {
    @GetMapping("/city/{cityName}")
    public String getByCity(@PathVariable String cityName) {
        return "City " + cityName;
    }
    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public String getByCoordinates(@PathVariable String latitude,
                                   @PathVariable String longitude) {
        return "Coordinates Latitude " + latitude + " Longitude " + longitude;
    }

}
