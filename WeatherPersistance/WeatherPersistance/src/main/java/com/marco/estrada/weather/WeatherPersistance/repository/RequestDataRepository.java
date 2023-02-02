package com.marco.estrada.weather.WeatherPersistance.repository;

import com.marco.estrada.weather.WeatherPersistance.entity.RequestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestDataRepository extends JpaRepository<RequestData, Long> {
}
