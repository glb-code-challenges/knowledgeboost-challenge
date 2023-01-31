package com.globant.knowledgeboostchallenge.dto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WeatherDTO {
    private String name;
    private Object[] weather;
    private LocalDateTime dt;
    private String cod;
    private String message;
    private String type;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt  = LocalDateTime.ofEpochSecond(Long.parseLong(dt), 0, ZoneOffset.of("-06:00"));

    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Object[] getWeather() {
        return weather;
    }

    public void setWeather(Object[] weather) {
        this.weather = weather;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
