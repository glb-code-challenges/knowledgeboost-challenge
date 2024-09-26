package com.glb.knowledgeboostchallenge.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Snow {

    @JsonProperty("1h")
    private String snowVolumeOneHr;

    @JsonProperty("3h")
    private String snowVolumeThreeHr;

}
