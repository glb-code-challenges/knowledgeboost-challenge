package com.glo.tp.challenge.weatherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WindDTO {
	
	private double speed;
	private int deg;
	private double gust;

}
