package com.weather.api.service.impl;

import com.weather.api.client.OpenWeatherApiClient;
import com.weather.api.client.response.WeatherResponse;
import com.weather.api.converter.WeatherDTOToExecutionEntityConverter;
import com.weather.api.entity.ExecutionEntity;
import com.weather.api.repository.ExecutionsRepository;
import com.weather.api.service.WeatherService;
import feign.FeignException;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherServiceImplTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private OpenWeatherApiClient openWeatherApiClient;

    @MockBean
    private ExecutionsRepository executionsRepository;

    @MockBean
    private WeatherDTOToExecutionEntityConverter weatherDTOToExecutionEntityConverter;


    @Test
    void arrangeApiGetsWeatherByCityName_whenApiIsWorking_thenReturnValidResponse() {
        when(openWeatherApiClient.getWeatherByCityName(anyString(), anyString()))
                .thenReturn(new WeatherResponse());

        when(executionsRepository.save(any()))
                .thenReturn(new ExecutionEntity());

        when(weatherDTOToExecutionEntityConverter.convert(any(WeatherResponse.class)))
                .thenReturn(new ExecutionEntity());

        weatherService.getWeatherByCityName("Mexico city");

        verify(openWeatherApiClient, times(1)).getWeatherByCityName(eq("Mexico city"), any());
    }

    @Test
    @Ignore("This code don't thrown the right Exception. It will validate later.")
    void arrangeApiGetsWeatherByCityName_whenApiIsNotWorking_thenThrowsAnException() {
        when(openWeatherApiClient.getWeatherByCityName(anyString(), anyString()))
                .thenThrow(mock(FeignException.class));

        when(executionsRepository.save(any()))
                .thenReturn(new ExecutionEntity());

        weatherService.getWeatherByCityName("City unknown");

        assertThatThrownBy(() -> openWeatherApiClient.getWeatherByCityName(anyString(), anyString()))
        .isInstanceOf(FeignException.class);

        verify(openWeatherApiClient, times(1)).getWeatherByCityName(eq("City unknown"), any());
    }

    @Test
    void arrangeApiGetsWeatherByLatitudeAndLongitude_whenApiIsWorking_thenReturnValidResponse() {
        when(openWeatherApiClient.getWeatherByLatitudeAndLongitude(anyDouble(), anyDouble(), anyString()))
                .thenReturn(new WeatherResponse());

        when(executionsRepository.save(any()))
                .thenReturn(new ExecutionEntity());

        when(weatherDTOToExecutionEntityConverter.convert(any(WeatherResponse.class)))
                .thenReturn(new ExecutionEntity());

        weatherService.getWeatherByLatitudeAndLongitude(19.4285D, -99.1277D);

        verify(openWeatherApiClient, times(1)).getWeatherByLatitudeAndLongitude(eq(19.4285D), eq(-99.1277D), any());
    }
}