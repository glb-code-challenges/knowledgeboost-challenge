package com.weatherapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.weatherapi.model.entity.WeatherEntity;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherEntity, Long>{
	

}
