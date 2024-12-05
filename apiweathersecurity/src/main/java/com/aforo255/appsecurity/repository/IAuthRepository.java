package com.aforo255.appsecurity.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aforo255.appsecurity.model.AccessModel;

@Repository
public interface IAuthRepository extends CrudRepository<AccessModel, Long> {
    
}

