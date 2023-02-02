
package com.weather.api.client.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "coord",
    "weather",
    "base",
    "main",
    "visibility",
    "wind",
    "clouds",
    "dt",
    "sys",
    "timezone",
    "id",
    "name",
    "cod"
})
@Getter
@Setter
public class WeatherResponse {

    @JsonProperty("coord")
    @Valid
    private Coord coord;
    @JsonProperty("weather")
    @Valid
    private List<Weather> weather;
    @JsonProperty("base")
    private String base;
    @JsonProperty("main")
    @Valid
    private Main main;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind")
    @Valid
    private Wind wind;
    @JsonProperty("clouds")
    @Valid
    private Clouds clouds;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("sys")
    @Valid
    private Sys sys;
    @JsonProperty("timezone")
    private Integer timezone;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private Integer cod;

}
