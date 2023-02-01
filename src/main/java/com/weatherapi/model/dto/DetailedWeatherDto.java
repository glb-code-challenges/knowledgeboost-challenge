package com.weatherapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedWeatherDto {
	
	@JsonProperty("temp")
	private Float temperatue;
	
	@JsonProperty("feels_like")
	private Float feelsLike;
	
	@JsonProperty("temp_min")
	private Float minTemperature;
	
	@JsonProperty("temp_max")
	private Float maxTemperature;
	
	//@JsonProperty("pressure")
	private Integer pressure;
	
	//@JsonProperty("humidity")
	private Integer humidity;

}
