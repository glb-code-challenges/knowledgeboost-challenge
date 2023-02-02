package com.weatherapi.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="WEATHER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "WEATHER_ID")
	private Long id;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	@Column(name = "CREATED_ON")
	private LocalDateTime createdOn;
	
	@Column(name = "RESPONSE_CODE")
	private Long responseCode;
	
	
	
	

}
