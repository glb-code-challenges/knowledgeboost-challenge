package com.example.group.demoArtifact01.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    //@Query("SELECT c FROM City c WHERE c.name = ?1")
    Optional<City> findCityByName(String name);
}
