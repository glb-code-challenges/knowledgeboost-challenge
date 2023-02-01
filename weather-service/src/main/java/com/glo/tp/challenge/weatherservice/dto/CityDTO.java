package com.glo.tp.challenge.weatherservice.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class CityDTO {
	
	private long id;
	private String name;
	@JsonProperty("timezone")
	private int timeZone;
	private int cod;
	private String base;
	private int visibility;
	private int dt;
	private CoordDTO coord;
	private List<WeatherDTO> weather;
	private MainDTO main;
	private WindDTO wind;
	private CloudsDTO clouds;
	private SysDTO sys;
}
