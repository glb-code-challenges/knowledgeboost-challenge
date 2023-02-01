package com.codechallenge.openweather.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class Main implements Serializable {

	private Double temp;
	private Double feels_like;
	private Double temp_min;
	private Double temp_max;
	private Long pressure;
	private Long humidity;
	
	private static final long serialVersionUID = -4817721765961040017L;

}
