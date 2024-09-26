package com.glb.knowledgeboostchallenge.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rain {

    @JsonProperty("1h")
    private String rainVolumeOneHr;

    @JsonProperty("3h")
    private String rainVolumeThreeHr;

}
