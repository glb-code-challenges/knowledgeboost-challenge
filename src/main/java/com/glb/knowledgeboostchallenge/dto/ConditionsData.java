package com.glb.knowledgeboostchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConditionsData {

    @ApiModelProperty(notes="Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.", example="7.2" )
    private Double windSpeed;

    @ApiModelProperty(notes="Wind direction, degrees (meteorological)")
    private String windDirection;

    @ApiModelProperty(notes="Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour", example="80" )
    private Double windGust;

    @ApiModelProperty(notes="Rain volume for the last 1 hour, mm", example="0.19")
    private String rainVolumeOneHr;

    @ApiModelProperty(notes="Rain volume for the last 3 hours, mm", example="0.20")
    private String rainVolumeThreeHr;

    @ApiModelProperty(notes="Snow volume for the last 1 hour, mm", example="0.19")
    private String snowVolumeOneHr;

    @ApiModelProperty(notes="Snow volume for the last 3 hours, mm", example="0.20")
    private String snowVolumeThreeHr;

}
