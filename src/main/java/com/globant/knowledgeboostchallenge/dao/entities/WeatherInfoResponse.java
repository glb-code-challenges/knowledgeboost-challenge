package com.globant.knowledgeboostchallenge.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.sql.Timestamp;
import java.util.List;

@Document(value = "weather")
@Data
public class WeatherInfoResponse {

    private double longitude;
    private double latitude;
    private List<String> weather;
    private String temp;
    private String tempMin;
    private String tempMax;
    public int pressure;
    public int humidity;
    private Timestamp sunrise;
    private Timestamp sunset;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Timestamp timestamp;
    private Integer responseCode;
    private String cityName;
    private String errorCause;
}
