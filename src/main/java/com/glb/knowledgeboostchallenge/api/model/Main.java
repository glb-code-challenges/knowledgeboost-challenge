package com.glb.knowledgeboostchallenge.api.model;

import lombok.Data;

@Data
public class Main {

    private double temp;
    private double feels_like;
    private double temp_min;
    private double  temp_max;
    private String  pressure;
    private String  humidity;
    private String  sea_level;
    private String  grnd_level;

}
