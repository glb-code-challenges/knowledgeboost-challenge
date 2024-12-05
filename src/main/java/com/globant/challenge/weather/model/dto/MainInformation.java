package com.globant.challenge.weather.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainInformation implements Serializable {

	private static final long serialVersionUID = 7806554986791028449L;
	private Double temp;
	@JsonAlias("feels_like")
	private Double feelsLike;
	@JsonAlias("temp_min")
	private Double tempMin;
	@JsonAlias("temp_max")
	private Double tempMax;
	@JsonAlias("pressure")
	private Integer pressure;
	@JsonAlias("humidity")
	private Integer humidity;
	@JsonAlias("sea_level")
	private Integer seaLevel;
	@JsonAlias("grnd_level")
	private Integer grndLevel;

}
