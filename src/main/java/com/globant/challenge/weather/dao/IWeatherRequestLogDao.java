package com.globant.challenge.weather.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.challenge.weather.model.entity.WeatherRequestLog;

public interface IWeatherRequestLogDao extends JpaRepository<WeatherRequestLog, UUID> {
}
