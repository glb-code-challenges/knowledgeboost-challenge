package com.globant.knowledgeboostchallenge.dao.dtos;

import lombok.Data;

@Data
public class MainDto {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;
}