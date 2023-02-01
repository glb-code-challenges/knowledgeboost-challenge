package com.weather.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCityName {

    @NotBlank
    private String cityName;
}
