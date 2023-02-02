package com.globant.knowledgeboostchallenge.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.knowledgeboostchallenge.dao.dtos.WeatherInfoDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Log4j2
@ExtendWith(MockitoExtension.class)
class WeatherUtilTest {
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @InjectMocks private WeatherUtil weatherUtil;
    @Mock private ResponseEntity<WeatherInfoDto> infoDtoResponseEntityExpected;

    @BeforeEach
    void setUp() throws IOException {
        WeatherInfoDto infoDto = OBJECT_MAPPER.readValue(
                WeatherUtilTest.class.getClassLoader().getResourceAsStream("weatherInfoDto.json"), WeatherInfoDto.class);
        infoDtoResponseEntityExpected= new ResponseEntity(infoDto, HttpStatus.OK);

    }

    @Test
    void getWeatherInfoResponse() {
        var actual = weatherUtil.getWeatherInfoResponse(infoDtoResponseEntityExpected);
        Assertions.assertNotNull(actual);
    }
}