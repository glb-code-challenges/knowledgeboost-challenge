package com.glb.knowledgeboostchallenge.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Wind {

    private Double speed;
    private String deg;
    private Double gust;

}
