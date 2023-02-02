package com.glb.knowledgeboostchallenge.repository;

import com.glb.knowledgeboostchallenge.model.ResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<ResponseLog, Integer> {

}
