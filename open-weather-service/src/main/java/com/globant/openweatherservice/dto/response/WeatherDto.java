package com.globant.openweatherservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherDto {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
