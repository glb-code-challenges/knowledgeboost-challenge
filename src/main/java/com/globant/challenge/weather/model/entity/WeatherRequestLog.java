package com.globant.challenge.weather.model.entity;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weather_request_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequestLog {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@ColumnDefault("gen_random_uuid()")
	private UUID id;
	@Column(name = "city_name")
	private String cityName;
	@Column(name = "response_code")
	private Integer responseCode;
	@Column(name = "response_message")
	private String responseMessage;
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	@Column(name = "created_at")
	private Date createdAt = new Date();

}
