package com.globant.dnlpalestina.WeatherService.models.entities;

import lombok.Data;
import net.minidev.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class WeatherDetails implements Serializable {
    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public Long visibility;
    public Wind wind;
    public Long timezone;
    public Long id;
    public String name;
    public Integer cod;
    public String message;
}


