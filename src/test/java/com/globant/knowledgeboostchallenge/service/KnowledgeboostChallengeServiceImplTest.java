package com.globant.knowledgeboostchallenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.knowledgeboostchallenge.dto.WeatherDTO;
import com.globant.knowledgeboostchallenge.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KnowledgeboostChallengeServiceImplTest {

    @InjectMocks
    KnowledgeboostChallengeServiceImpl knowledgeboostChallengeServiceImpl;
    @Mock
    RestTemplate restTemplate;

    @Mock
    ModelMapper modelMapper;

    @Mock
    WeatherRepository weatherRepository;

    @Test
    public void testgetByCity() throws JsonProcessingException {
        String body = getByCityResponse();
        when(restTemplate.getForEntity(eq("https://api.openweathermap.org/data/2.5/weather?q=Aguascalientes&appid=null"), any()))
                .thenReturn(new ResponseEntity(body, HttpStatus.OK));
        WeatherDTO weatherDTO = knowledgeboostChallengeServiceImpl
                .getByCity("Aguascalientes");
        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getType()).isEqualTo("BY_CITY_NAME");
        assertThat(weatherDTO.getCod()).isEqualTo("200");
    }

    @Test
    public void testgetByCoordinates() throws JsonProcessingException {
        String body = getByCoordinatesResponse();
        when(restTemplate.getForEntity(eq("https://api.openweathermap.org/data/2.5/weather?lat=20&lon=50&appid=null"), any()))
                .thenReturn(new ResponseEntity(body, HttpStatus.OK));
        WeatherDTO weatherDTO = knowledgeboostChallengeServiceImpl
                .getByCoordinates("20", "50");
        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getType()).isEqualTo("BY_COORDINATES");
        assertThat(weatherDTO.getCod()).isEqualTo("200");
    }

    private String getByCityResponse() {
        return "{\n" +
                "    \"name\": \"Aguascalientes\",\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 803,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"broken clouds\",\n" +
                "            \"icon\": \"04d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"cod\": \"200\",\n" +
                "    \"message\": null,\n" +
                "    \"type\": \"BY_CITY_NAME\"\n" +
                "}";
    }
    private String getByCoordinatesResponse() {
        return "{\n" +
                "    \"name\": \"Aguascalientes\",\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 803,\n" +
                "            \"main\": \"Clouds\",\n" +
                "            \"description\": \"broken clouds\",\n" +
                "            \"icon\": \"04d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"cod\": \"200\",\n" +
                "    \"message\": null,\n" +
                "    \"type\": \"BY_COORDINATES\"\n" +
                "}";
    }

}
