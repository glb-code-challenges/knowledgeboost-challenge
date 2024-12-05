package com.aforo255.appdeposit.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
	private String temp;
	@JsonProperty("feels_like")
	private String feelsLike;
	@JsonProperty("temp_min")
	private String tempMin;
	@JsonProperty("temp_max")
	private String tempMax;
	private String pressure;
	private String humidity;
	@JsonProperty("sea_level")
	private String seaLevel;
	@JsonProperty("grnd_level")
	private String grndLevel;
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(String feelsLike) {
		this.feelsLike = feelsLike;
	}
	public String getTempMin() {
		return tempMin;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public String getTempMax() {
		return tempMax;
	}
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getSeaLevel() {
		return seaLevel;
	}
	public void setSeaLevel(String seaLevel) {
		this.seaLevel = seaLevel;
	}
	public String getGrndLevel() {
		return grndLevel;
	}
	public void setGrndLevel(String grndLevel) {
		this.grndLevel = grndLevel;
	}
}
