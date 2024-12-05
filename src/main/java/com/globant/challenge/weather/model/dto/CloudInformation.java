package com.globant.challenge.weather.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudInformation implements Serializable {

	private static final long serialVersionUID = 2165608685767182276L;
	private Integer all;

}
