package com.codechallenge.openweather.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class Weather implements Serializable {

	private Long id;
	private String main;
	private String description;
	private String icon;
	
	private static final long serialVersionUID = -8038162664864755274L;

	
}
