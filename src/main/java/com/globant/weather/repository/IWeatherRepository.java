package com.globant.weather.repository;

import com.globant.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeatherRepository extends JpaRepository<Weather, Integer> {
}
