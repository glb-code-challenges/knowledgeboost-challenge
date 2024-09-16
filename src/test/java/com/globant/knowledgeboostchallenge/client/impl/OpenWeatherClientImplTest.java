package com.globant.knowledgeboostchallenge.client.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.knowledgeboostchallenge.client.config.ClientUtil;
import com.globant.knowledgeboostchallenge.dao.dtos.WeatherInfoDto;
import com.globant.knowledgeboostchallenge.utils.URLUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({OpenWeatherClientImpl.class, ClientUtil.class})
@SpringBootTest(classes = {RestTemplate.class},properties = {"url.open_weather=http://api.openweathermap.org/data/2.5/weather"})
class OpenWeatherClientImplTest {
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String latitude="19.3584097";
    private static final String longitude="-99.0608733";
    private static final String appId="18e9633e9829e4d1b05adf1723de2bb1";
    private static final String cityName="London";


    @InjectMocks private OpenWeatherClientImpl client;
    @Mock private MockRestServiceServer mockServer;

    @Mock private RestTemplate restTemplate;
    @Mock private HttpEntity<String> httpEntity;

    private WeatherInfoDto info;
    private  String json;

    @BeforeEach
    void setUp() throws IOException {
        HttpHeaders myheaders = new HttpHeaders();
        myheaders.set("Gerardo", "Gerardo");
        httpEntity=new HttpEntity<>(myheaders);

        mockServer = MockRestServiceServer.createServer(restTemplate);

         info = OBJECT_MAPPER.readValue(
                 OpenWeatherClientImplTest.class.getClassLoader().getResourceAsStream("weatherInfoDto.json"), WeatherInfoDto.class);
         json = new ObjectMapper().writeValueAsString(info);

    }

    @Test
    void getWeatherByLatAndLonAndAppId() {
        mockServer.expect(ExpectedCount.once(),
                        requestTo(
                                new URLUtil("http://api.openweathermap.org/data/2.5/weather?lat=19.3584097&lon=-99.0608733&appId=18e9633e9829e4d1b05adf1723de2bb1")
                                        .build()
                        )
                )
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(json)
                );

        var actual =client.getWeatherByLatAndLonAndAppId(latitude,longitude,appId);
        //Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Assertions.assertNull(actual);

    }

    @Test
    void getWeatherByCityAndAppId() {
        mockServer.expect(ExpectedCount.once(),
                        requestTo(
                                new URLUtil("https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=18e9633e9829e4d1b05adf1723de2bb1")
                                        .build()
                        )
                )
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(json)
                );
        var actual =client.getWeatherByCityAndAppId(cityName,appId);
        //Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Assertions.assertNull(actual);
    }
}