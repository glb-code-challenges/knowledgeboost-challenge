package com.aforo255.appdeposit.dto;

import java.io.Serializable;

import com.aforo255.appdeposit.model.WeatherModel;

public class ApiWeatherServiceResponse implements Serializable {

	private static final long serialVersionUID = -1565722479874710527L;
	
	private String code;
	private String message;
	private WeatherModel weather;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public WeatherModel getWeather() {
		return weather;
	}
	public void setWeather(WeatherModel weather) {
		this.weather = weather;
	}

}
