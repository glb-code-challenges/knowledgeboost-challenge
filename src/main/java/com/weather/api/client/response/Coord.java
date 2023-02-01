
package com.weather.api.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lon",
    "lat"
})
@Getter
@Setter
public class Coord {

    @JsonProperty("lon")
    @DecimalMin("-180.0") @DecimalMax("180.0") @Digits(integer=3, fraction=6)
    private Double lon;
    @JsonProperty("lat")
    @DecimalMin("-90.0") @DecimalMax("90.0") @Digits(integer = 2, fraction = 6)
    private Double lat;

}
