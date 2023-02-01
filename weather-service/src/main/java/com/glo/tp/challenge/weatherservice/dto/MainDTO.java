package com.glo.tp.challenge.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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