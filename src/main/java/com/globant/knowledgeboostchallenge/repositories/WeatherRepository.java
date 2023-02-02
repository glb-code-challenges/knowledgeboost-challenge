package com.globant.knowledgeboostchallenge.repositories;

import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepository extends MongoRepository<WeatherInfoResponse,Long> {

}
