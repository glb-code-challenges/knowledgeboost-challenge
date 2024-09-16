package com.globant.knowledgeboostchallenge.dao.dtos;

import lombok.Data;

@Data
public class WeatherDto {
    private int id;
    private String main;
    private String description;
    private String icon;
}
