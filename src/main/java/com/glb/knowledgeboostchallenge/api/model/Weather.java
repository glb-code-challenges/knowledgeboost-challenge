package com.glb.knowledgeboostchallenge.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {

    private long id;
    private String main;
    private String description;
    private String icon;

}
