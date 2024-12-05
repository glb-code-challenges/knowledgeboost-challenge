package com.aforo255.appdeposit.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.aforo255.appdeposit.client.dto.OpenWeatherResponse;

@Document(collection = "weather")
public class WeatherModel  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @BsonId
	private String id ;     
	private OpenWeatherResponse response;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime timestamp;
	private String cityName;
	private String lat;
	private String lon;
	private String message;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public OpenWeatherResponse getResponse() {
		return response;
	}
	public void setResponse(OpenWeatherResponse response) {
		this.response = response;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
}
