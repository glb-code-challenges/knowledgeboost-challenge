package com.globant.challenge.weather.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResume implements Serializable {

	private static final long serialVersionUID = 3869055665786956411L;
	private Integer id;
	private String main;
	private String description;
	private String icon;

}
