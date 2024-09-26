package com.codechallenge.openweather.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WeatherBase implements Serializable {

	private Coordinates coord;
	private List<Weather> weather;
	private String base;
	private Main main;
	private Long visibility;
	private Wind wind;
	private Clouds clouds;
	private Long dt;
	private Sys sys;
	private Long timezone;
	private Long id;
	private String name;
	private Integer cod;
	private String mensaje;
	
	private static final long serialVersionUID = 7699790580345285252L;
}
