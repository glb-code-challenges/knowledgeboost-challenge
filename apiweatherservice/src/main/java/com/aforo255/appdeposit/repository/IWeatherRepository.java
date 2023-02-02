package com.aforo255.appdeposit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aforo255.appdeposit.model.WeatherModel;

@Repository
public interface IWeatherRepository extends MongoRepository<WeatherModel, String>{
    
}