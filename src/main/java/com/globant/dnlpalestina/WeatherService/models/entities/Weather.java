package com.globant.dnlpalestina.WeatherService.models.entities;

import lombok.Data;

@Data
public class Weather {
    public int id;
    public String main;
    public String description;
    public String icon;
}
