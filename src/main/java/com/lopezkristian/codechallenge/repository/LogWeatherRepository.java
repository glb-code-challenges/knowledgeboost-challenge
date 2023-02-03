package com.lopezkristian.codechallenge.repository;

import com.lopezkristian.codechallenge.model.LogWeather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogWeatherRepository extends CrudRepository<LogWeather, Long> {
}
