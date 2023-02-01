package com.glo.tp.challenge.weatherservice.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class SysDTO {
	
	private int type;
	private long id;
	private String country;
	private int sunrise;
	private int sunset;
}