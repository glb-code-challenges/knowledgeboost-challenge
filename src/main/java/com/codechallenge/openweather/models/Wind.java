package com.codechallenge.openweather.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Wind implements Serializable {

	private Double speed;
	private Long deg;
	
	private static final long serialVersionUID = 1220630238401055958L;

}
