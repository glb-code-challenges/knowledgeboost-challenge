package com.globant.dnlpalestina.WeatherService.repositories;

import com.globant.dnlpalestina.WeatherService.models.entities.WeatherEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
}
