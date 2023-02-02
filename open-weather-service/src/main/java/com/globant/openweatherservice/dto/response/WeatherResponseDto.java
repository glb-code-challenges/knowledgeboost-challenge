package com.globant.openweatherservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class WeatherResponseDto {
    private CoordinatesDto coord;
    private ArrayList<WeatherDto> weather;
    private String base;
    private MainDto main;
    private Integer visibility;
    private WindDto wind;
    private CloudsDto clouds;
    private Long dt;
    private SysDto sys;
    private Long timezone;
    private Long id;
    private String name;
    private Integer cod;

}
