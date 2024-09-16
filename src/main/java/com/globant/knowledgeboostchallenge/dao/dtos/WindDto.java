package com.globant.knowledgeboostchallenge.dao.dtos;

import lombok.Data;

@Data
public class WindDto {
    private double speed;
    private int deg;
    private double gust;
}
