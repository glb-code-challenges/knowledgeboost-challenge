package com.glb.knowledgeboostchallenge.utils;

import com.glb.knowledgeboostchallenge.api.model.OpenWeatherResponse;
import com.glb.knowledgeboostchallenge.dto.WeatherResponse;
import com.glb.knowledgeboostchallenge.exception.ConvertApiResponseException;
import com.glb.knowledgeboostchallenge.model.ResponseLog;
import org.junit.jupiter.api.Test;

import static com.glb.knowledgeboostchallenge.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherUtilsTest {

    @Test
    public void When_ApiResponseMessage_Expected_ConvertedFinalResponse()  {
        OpenWeatherResponse response = OpenWeatherResponse.builder()
            .cod(404)
            .message(MESSAGE_CITY_NOT_FOUND)
            .build();
        WeatherResponse res = WeatherUtils.convertWeatherResponse(response);
        assertNotNull(res);
        assertEquals(MESSAGE_CITY_NOT_FOUND, res.getMessage());
    }

    @Test
    public void When_ApiResponse_Expected_ConvertedFinalResponse()  {
        OpenWeatherResponse response = OpenWeatherResponse.builder()
            .cod(200)
            .name("Tokyo")
            .build();
        WeatherResponse res = WeatherUtils.convertWeatherResponse(response);
        assertNotNull(res);
        assertEquals("Tokyo", res.getName());
    }

    @Test
    public void When_ApiResponse_Expected_DBModel () {
        OpenWeatherResponse openWeatherResponse = OpenWeatherResponse.builder()
            .name("London")
            .cod(200)
            .id(12L)
            .dt(1675303364L)
            .build();
        String responseBody = "some body from a response";
        ResponseLog log = WeatherUtils.createResponseLog(openWeatherResponse, responseBody);
        assertNotNull(log);
        assertEquals(200, log.getResponseCode());
    }

    @Test
    public void When_CorrectResponseBody_Expected_ConvertedJavaObject () throws ConvertApiResponseException {
        String body = "{\"message\":\"This is a test\",\"cod\":200}";
        OpenWeatherResponse res = WeatherUtils.getResponseApi(body);
        assertNotNull(res);
        assertEquals("This is a test", res.getMessage());
    }

    @Test
    public void When_WrongResponseBody_Expected_ConvertedJavaObject () {
        String body = "{\"myMessage\":\"This is a test\",\"cod\":200}";
        assertThrows(ConvertApiResponseException.class, () -> WeatherUtils.getResponseApi(body));
    }

}
