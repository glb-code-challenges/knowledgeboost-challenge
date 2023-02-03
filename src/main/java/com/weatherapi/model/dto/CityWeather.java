package com.weatherapi.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityWeather {
	
	private String name;
	
	@JsonProperty(value = "weather")
	private List<GeneralWeatherDto> generalWeather;
	
	@JsonProperty(value = "main")
	private DetailedWeatherDto detailedWeather;

}
