package com.glo.tp.challenge.weatherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glo.tp.challenge.weatherservice.domain.WeatherHistory;

public interface WeatherRepository extends JpaRepository<WeatherHistory, Long> {

}