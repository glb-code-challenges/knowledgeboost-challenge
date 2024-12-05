package com.aforo255.appdeposit.client.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class BaseOpenWeatherResponse implements Serializable{

	private static final long serialVersionUID = -7397327901678734921L;
	
	private String cod;
	private String message;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}


