package com.glb.knowledgeboostchallenge.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

    private Double lon;
    private Double lat;

}
