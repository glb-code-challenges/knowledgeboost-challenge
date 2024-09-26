package com.glb.knowledgeboostchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRequest {

    public WeatherRequest(String apiKey, String cityName) {
        this.apiKey = apiKey;
        this.cityName = cityName;
    }

    public WeatherRequest(String apiKey, String latitude, String longitude) {
        this.apiKey = apiKey;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private String apiKey;
    private String cityName;
    private String latitude;
    private String longitude;

}
