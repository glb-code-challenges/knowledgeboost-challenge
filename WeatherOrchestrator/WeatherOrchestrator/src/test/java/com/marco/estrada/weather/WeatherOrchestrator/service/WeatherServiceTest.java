package com.marco.estrada.weather.WeatherOrchestrator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.estrada.weather.WeatherOrchestrator.client.WeatherClient;
import com.marco.estrada.weather.WeatherOrchestrator.dto.Position;
import com.marco.estrada.weather.WeatherOrchestrator.dto.WeatherResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @InjectMocks
    WeatherService service;

    @Mock
    WeatherClient client;

    @Mock
    PublishServiceRabbit publishServiceRabbit;

    @BeforeEach
    void setUp() {
        when(client.getWeather(anyString(), anyDouble(), anyDouble(), anyString()))
                .thenReturn(buildRespFromJson());
        service.apiKey = "myKey";
    }

    @Test
    void getCityWeather() {

        WeatherResp resp = service.getCityWeather("Mexico");
        Assertions.assertNotNull(resp);
        Assertions.assertEquals("Mexico", resp.getName());
    }

    @Test
    void getPositionWeather() {
        WeatherResp resp = service.getPositionWeather(
                Position.builder()
                        .longitude(-119.4432)
                        .latitude(36.4761)
                        .build());
        Assertions.assertNotNull(resp);
        Assertions.assertEquals("Mexico", resp.getName());
    }

    private WeatherResp buildRespFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String value = Files.readString(Paths.get(Objects.requireNonNull(this.getClass()
                    .getClassLoader()
                    .getResource("response.json")).toURI()));
            return mapper.readValue(value, WeatherResp.class);

        } catch (URISyntaxException exception) {
            log.error("Error getting json from property ", exception);
            return WeatherResp.builder().build();
        } catch (IOException exception) {
            log.error("Error parsing json ", exception);
            return WeatherResp.builder().build();
        }
    }

}