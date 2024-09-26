package com.glb.knowledgeboostchallenge.dto;

import java.util.List;

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
public class WeatherResponse {

    @ApiModelProperty(notes = "City ID", example = "456")
    private Long id;

    @ApiModelProperty(notes = "City name", example = "Tokyo")
    private String name;

    @ApiModelProperty(notes = "Country code (GB, JP etc.)", example = "JP")
    private String countryCode;

    @ApiModelProperty(notes="Shift in seconds from UTC", example="-21600")
    private String timezone;

    @ApiModelProperty(notes="City geo location, longitude", example="139.6917")
    private Double longitude;

    @ApiModelProperty(notes="City geo location, latitude", example="35.6895")
    private Double latitude;

    @ApiModelProperty(notes="List of weather conditions")
    private List<WeatherCondition> weatherConditions;

    @ApiModelProperty(notes="Temperature information")
    private Temperature temperature;

    @ApiModelProperty(notes="Visibility, meter. The maximum value of the visibility is 10km", example = "10000")
    private String visibility;

    @ApiModelProperty(notes="List of conditions weather exact information")
    private ConditionsData conditionsData;

    @ApiModelProperty(notes="Percentage (%) of cloudiness", example ="75")
    private String cloudiness;

    @ApiModelProperty(notes = "Time of data calculation, unix, UTC", example="1675296569")
    private Long dataTimeCalculation;

    @ApiModelProperty(notes = "Sunrise time, unix, UTC", example="1675287672")
    private String sunriseTime;

    @ApiModelProperty(notes = "Sunset time, unix, UTC", example="1675325303")
    private String sunsetTime;

    @ApiModelProperty(notes = "Code of the response", example = "200")
    private Integer code;

    @ApiModelProperty(notes = "Message send when something was different to Ok", example="city not found")
    private String message;

}
