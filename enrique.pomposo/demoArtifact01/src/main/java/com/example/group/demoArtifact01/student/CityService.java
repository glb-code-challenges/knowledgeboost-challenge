package com.example.group.demoArtifact01.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public void addNewCity(City city){
        System.out.println("city name: " + city.getName());
        Optional<City> cityOptional = cityRepository.findCityByName(city.getName());
        System.out.println("found: " + cityOptional);
        if (cityOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        cityRepository.save(city);
    }
}
