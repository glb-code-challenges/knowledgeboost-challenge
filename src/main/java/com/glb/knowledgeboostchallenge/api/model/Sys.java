package com.glb.knowledgeboostchallenge.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sys {

    private String type;
    private String id;
    private String country;
    private String sunrise;
    private String sunset;

}
