package com.glb.knowledgeboostchallenge.service.impl;

import com.glb.knowledgeboostchallenge.AbstractTest;
import com.glb.knowledgeboostchallenge.dto.WeatherRequest;
import com.glb.knowledgeboostchallenge.exception.BaseWeatherException;
import com.glb.knowledgeboostchallenge.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceImplTest extends AbstractTest {

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private WeatherServiceImpl weatherService;

    //@Test
    public void When_RightCity_Expected_Response () throws BaseWeatherException {
        WeatherRequest request = createRequestByCity();
        weatherService.getWeatherByCity(request);

    }

}
