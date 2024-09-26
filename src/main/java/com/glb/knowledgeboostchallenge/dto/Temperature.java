package com.glb.knowledgeboostchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Temperature {

    @ApiModelProperty(notes = "Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.", example="278.76")
    private double temperature;

    @ApiModelProperty(notes = "This temperature parameter accounts for the human perception of weather. Unit Default: "
        + "Kelvin, Metric: Celsius, Imperial: Fahrenheit.", example="273.11")
    private double perception;

    @ApiModelProperty(notes = "Minimum temperature at the moment. This is minimal currently observed temperature (within "
        + "large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.", example="276.9")
    private double minimumTemperature;

    @ApiModelProperty(notes = "Maximum temperature at the moment. This is maximal currently observed temperature (within "
        + "large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.", example="280.14")
    private double  maximumTemperature;

    @ApiModelProperty(notes = "Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), "
        + "hPa", example="1017")
    private String  atmosphericPressure;

    @ApiModelProperty(notes = "Percentage (%) of humidity", example="37")
    private String  humidity;

    @ApiModelProperty(notes = "Atmospheric pressure on the sea level, hPa", example="")
    private String  seaLevel;

    @ApiModelProperty(notes = "Atmospheric pressure on the ground level, hPa", example="")
    private String  groundLevel;

}
