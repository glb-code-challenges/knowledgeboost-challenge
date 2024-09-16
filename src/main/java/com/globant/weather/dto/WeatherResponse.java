package com.globant.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private Coord coord;
    private List<Weather> weather;
    private String Base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timeZone;
    private int id;
    private String name;
    private int cod;
}
