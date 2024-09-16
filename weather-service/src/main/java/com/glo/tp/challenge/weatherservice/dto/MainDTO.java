package com.glo.tp.challenge.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class MainDTO {
	
	private double temp;
	@JsonProperty("feels_like")
	private double feelsKile;
	@JsonProperty("temp_min")
	private double tempMin;
	@JsonProperty("temp_max")
	private double tempMax;
	private int pressure;
	private int humidity;
	@JsonProperty("sea_level")
	private int seaLevel;
	@JsonProperty("grnd_level")
	private int grndLevel;
}