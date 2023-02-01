package com.glo.tp.challenge.weatherservice.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weather_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherHistory {
	
	@Id
	@GeneratedValue(generator = "WEATHER_HISTORY_SEQUENCE")
	@Column(name = "id")
	private Long id;

	@Column(name = "consulted_by")
	private String consultedBy;

	@Column(name = "city_name")
	private String cityName;
	
	@Column(name = "response_code")
	private int code;
	
	@Column(name = "operation_date")
	private LocalDate operationDate;
	
	@Column(name = "message")
	private String message;

	@Column(name = "latitude")
	private float latitude;

	@Column(name = "longitude")
	private float longitude;
}