package com.globant.dnlpalestina.WeatherService.services;

import com.globant.dnlpalestina.WeatherService.models.entities.WeatherEntity;
import com.globant.dnlpalestina.WeatherService.repositories.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private WeatherEntity weatherEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherEntity = new WeatherEntity();
        weatherEntity.setId(100);
        weatherEntity.setCreatedAt(new Date());
        weatherEntity.setResponseCode(404);
        weatherEntity.setCityName("TestMéxico");
        weatherEntity.setCoords("Coord(lon=120.7198, lat=15.0646)");
        weatherEntity.setRootCause("city  not found!");
    }

    @Test
    void findAll() {
        when(weatherRepository.findAll()).thenReturn(Arrays.asList(weatherEntity));
        assertNotNull(weatherService.findAll());
    }
}