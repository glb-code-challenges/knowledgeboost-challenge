package com.globant.knowledgeboostchallenge.repository;

import com.globant.knowledgeboostchallenge.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
