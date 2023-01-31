package com.globant.knowledgeboostchallenge.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherDTOTest {

    @InjectMocks
    WeatherDTO weatherDTO;

    @Test
    public void testWeatherDTO(){

        weatherDTO = getWeatherDTO();
        assertThat(weatherDTO.getName()).isEqualTo("Aguascalientes");
        assertThat(weatherDTO.getDt()).isEqualTo("2023-01-31T14:17:52");
        assertThat(weatherDTO.getMessage()).isEqualTo("Testing DTO");
        assertThat(weatherDTO.getType()).isEqualTo("CITY");
        assertThat(weatherDTO.getCod()).isEqualTo("200");
    }
    private WeatherDTO getWeatherDTO() {
        WeatherDTO mockWeatherDTO = new WeatherDTO();
        mockWeatherDTO.setName("Aguascalientes");
        mockWeatherDTO.setMessage("Testing DTO");
        mockWeatherDTO.setType("CITY");
        mockWeatherDTO.setCod("200");
        mockWeatherDTO.setDt("1675196272");

        return mockWeatherDTO;
    }

}
