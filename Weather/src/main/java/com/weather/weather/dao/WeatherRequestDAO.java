package com.weather.weather.dao;

import com.weather.weather.entities.RequestWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRequestDAO extends JpaRepository<RequestWeather,Long> {
}
