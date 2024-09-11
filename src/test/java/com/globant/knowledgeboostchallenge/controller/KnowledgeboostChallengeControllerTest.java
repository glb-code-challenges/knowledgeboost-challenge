package com.globant.knowledgeboostchallenge.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.knowledgeboostchallenge.dto.WeatherDTO;
import com.globant.knowledgeboostchallenge.service.KnowledgeboostChallengeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class KnowledgeboostChallengeControllerTest {

    @InjectMocks
    KnowledgeboostChallengeController knowledgeboostChallengeController;

    @Mock
    WeatherDTO weatherDTO;
    @Mock
    KnowledgeboostChallengeService knowledgeboostChallengeService;

    @Test
    public void testGetByCity() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(knowledgeboostChallengeService.getByCity(any(String.class)))
                .thenReturn(getWeatherDTO());

        WeatherDTO weatherDTO = knowledgeboostChallengeController
                                    .getByCity("Aguascalientes");

        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getCod()).isEqualTo("200");
    }

    @Test
    public void testGetByCity_NotFound() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        WeatherDTO responseDTO = getWeatherDTO();
        responseDTO.setMessage("city not found");
        responseDTO.setCod("404");
        when(knowledgeboostChallengeService.getByCity(any(String.class)))
                .thenReturn(responseDTO);

        WeatherDTO weatherDTO = knowledgeboostChallengeController
                .getByCity("Aguascalientes");

        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getCod()).isEqualTo("404");
        assertThat(weatherDTO.getMessage()).isEqualTo("city not found");
    }

    @Test
    public void testGetByCoordinates() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(knowledgeboostChallengeService.getByCoordinates(any(String.class), any(String.class)))
                .thenReturn(getWeatherDTO());

        WeatherDTO weatherDTO = knowledgeboostChallengeController
                .getByCoordinates("50", "50");

        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getCod()).isEqualTo("200");
    }
    @Test
    public void testGetByCoordinates_WrongParameters() throws JsonProcessingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        WeatherDTO responseDTO = getWeatherDTO();
        responseDTO.setMessage("wrong longitude");
        responseDTO.setCod("400");
        when(knowledgeboostChallengeService.getByCoordinates(any(String.class), any(String.class)))
                .thenReturn(responseDTO);

        WeatherDTO weatherDTO = knowledgeboostChallengeController
                .getByCoordinates("AAA", "BBB");

        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getCod()).isEqualTo("400");
        assertThat(weatherDTO.getMessage()).isEqualTo("wrong longitude");
    }

    private WeatherDTO getWeatherDTO() {
        WeatherDTO mockWeatherDTO = new WeatherDTO();
        mockWeatherDTO.setName("Aguascalientes");
        mockWeatherDTO.setCod("200");

        return mockWeatherDTO;
    }
}
