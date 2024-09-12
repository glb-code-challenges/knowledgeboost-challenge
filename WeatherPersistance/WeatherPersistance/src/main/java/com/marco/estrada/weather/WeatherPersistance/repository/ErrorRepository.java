package com.marco.estrada.weather.WeatherPersistance.repository;

import com.marco.estrada.weather.WeatherPersistance.entity.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {

}
