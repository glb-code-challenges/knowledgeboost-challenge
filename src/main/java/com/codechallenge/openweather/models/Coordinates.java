package com.codechallenge.openweather.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Coordinates implements Serializable {

	private Double lon;
	private Double lat;
	
	private static final long serialVersionUID = -1118623050832346051L;

}
