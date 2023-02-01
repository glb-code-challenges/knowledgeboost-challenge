package com.glo.tp.challenge.weatherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysDTO {
	
	private int type;
	private long id;
	private String country;
	private int sunrise;
	private int sunset;
}