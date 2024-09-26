package com.codechallenge.openweather.models.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codechallenge.openweather.models.entity.WeatherData;

public interface WeatherDao extends JpaRepository<WeatherData, Long> {

	public List<WeatherData> findByCreateAtBetween(Date from, Date to);
}
