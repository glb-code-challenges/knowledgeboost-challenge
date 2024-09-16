package com.globant.talentpool.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherModel {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("timezone")
    @Expose
    private Long timezone;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("visibility")
    @Expose
    private Long visibility;
    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weatherList;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("snow")
    @Expose
    private Snow snow;
    @SerializedName("sys")
    @Expose
    private Sys sys;


}
