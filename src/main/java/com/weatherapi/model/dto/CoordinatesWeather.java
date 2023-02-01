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
public class CoordinatesWeather {
	
	private String name;
	
	@JsonProperty(value = "coord")
	private CoordinatesWeatherDto coordinatesWeather;
	
	

}
