package com.weather.weather.dao;

import com.weather.weather.entities.ResponseWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherResponseDAO extends JpaRepository<ResponseWeather,Long> {
}