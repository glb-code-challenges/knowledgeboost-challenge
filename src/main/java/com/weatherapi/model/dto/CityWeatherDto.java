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
public class CityWeatherDto {
	
	@JsonProperty(value = "name")
	private String cityName;
	
	@JsonProperty(value = "weather")
	private List<GeneralWeatherDto> generalWeather;
	
	@JsonProperty(value = "main")
	private DetailedWeatherDto detailedWeather;
	
	@JsonProperty(value = "cod")
	private Long responseCode;

}
