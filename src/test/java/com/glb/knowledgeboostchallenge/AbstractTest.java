package com.glb.knowledgeboostchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glb.knowledgeboostchallenge.dto.WeatherRequest;
import com.glb.knowledgeboostchallenge.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;

import static com.glb.knowledgeboostchallenge.utils.Constants.*;

public abstract class AbstractTest {

    @Value("${app.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${app.http.auth-token}")
    private String principalRequestValue;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static WeatherRequest createRequestByCity() {
        return WeatherRequest.builder()
            .cityName("London")
            .apiKey("6949ac5e4c9b64212d689f28b7c40263")
            .build();
    }

    public static WeatherRequest createRequestByWrongCity() {
        return WeatherRequest.builder()
            .cityName("1")
            .apiKey("6949ac5e4c9b64212d689f28b7c40263")
            .build();
    }

    public static WeatherRequest createRequestByCoordinates() {
        return WeatherRequest.builder()
            .longitude("10.99")
            .latitude("44.34")
            .apiKey("6949ac5e4c9b64212d689f28b7c40263")
            .build();
    }

    public static WeatherResponse createOkResponse() {
        return WeatherResponse.builder()
            .code(200)
            .name("London")
            .countryCode("GB")
            .build();
    }

    public static WeatherResponse createNotFoundCity() {
        return WeatherResponse.builder()
            .code(404)
            .message(MESSAGE_CITY_NOT_FOUND)
            .build();
    }

}
