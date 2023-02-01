package com.glo.tp.challenge.weatherservice.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class WeatherDTO {
	
	private long id;
	private String main;
	private String description;
	private String icon;
}
