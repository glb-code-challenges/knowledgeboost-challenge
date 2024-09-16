package com.globant.knowledgeboostchallenge.dao.dtos;


import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherInfoDto {
    private CoordDto coord;
    private ArrayList<WeatherDto> weather;
    private String base;
    private MainDto main;
    private int visibility;
    private WindDto wind;
    private CloudsDto clouds;
    private int dt;
    private SysDto sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
