package com.glb.knowledgeboostchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherCondition {

    @ApiModelProperty(notes = "id Weather condition", example="803")
    private long idContidion;
    @ApiModelProperty(notes = "Group of weather parameters (Rain, Snow, Extreme etc.)", example="Clouds")
    private String conditionType;
    @ApiModelProperty(notes = "Weather condition within the group", example="broken clouds")
    private String description;

}
