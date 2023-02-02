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
public class Coordinates implements Serializable {

	private static final long serialVersionUID = -4686448179172403841L;
	private String lon;
	private String lat;

}
