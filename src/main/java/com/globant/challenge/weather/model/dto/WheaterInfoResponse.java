package com.globant.challenge.weather.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Builder
public class WheaterInfoResponse implements Serializable {

	private static final long serialVersionUID = -8798917154193323991L;
	private Coordinates coord;
	private List<WeatherResume> weather;
	private String base;
	private MainInformation main;
	private Integer visibility;
	private WindInformation wind;
	private CloudInformation clouds;
	private Integer dt;
	private SysInformation sys;
	private Integer timezone;
	private Integer id;
	private String name;
	private Integer cod;

}
