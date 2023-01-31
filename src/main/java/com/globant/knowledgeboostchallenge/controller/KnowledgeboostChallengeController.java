package com.globant.knowledgeboostchallenge.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class KnowledgeboostChallengeController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/city/{cityName}")
    public String getByCity(@PathVariable String cityName) {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://localhost:8081/identity/message",
                String.class);
        return response.getBody();
        /*HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("
                http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();*/

        //return "City " + cityName;
    }
    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public String getByCoordinates(@PathVariable String latitude,
                                   @PathVariable String longitude) {
        return "Coordinates Latitude " + latitude + " Longitude " + longitude;
    }

}
