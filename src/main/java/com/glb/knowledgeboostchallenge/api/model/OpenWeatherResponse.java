package com.glb.knowledgeboostchallenge.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherResponse {

    private Coordinates coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private String visibility;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private String timezone;
    private Long id;
    private String name;
    private Integer cod;
    private String message;

}
