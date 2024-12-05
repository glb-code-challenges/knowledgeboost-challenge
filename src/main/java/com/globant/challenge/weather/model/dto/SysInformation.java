package com.globant.challenge.weather.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Builder
public class SysInformation implements Serializable {

	private static final long serialVersionUID = 4945044418264507248L;
	private Integer type;
	private Integer id;
	private String country;
	private Integer sunrise;
	private Integer sunset;

}
