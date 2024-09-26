package com.codechallenge.openweather.models;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class Sys implements Serializable {

	private String country;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Long sunrise;
	private Long sunset;
	
	private static final long serialVersionUID = -1565068718008470942L;

}
