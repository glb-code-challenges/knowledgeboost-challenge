package com.glo.tp.challenge.weatherservice.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class WindDTO {
	
	private double speed;
	private int deg;
	private double gust;

}
