package com.globant.challenge.weather.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WindInformation implements Serializable {

	private static final long serialVersionUID = 2411560210401351613L;
	private Double speed;
	private Integer deg;
	private Double gust;

}
