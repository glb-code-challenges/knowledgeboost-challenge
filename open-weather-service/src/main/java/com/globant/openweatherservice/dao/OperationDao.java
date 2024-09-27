package com.globant.openweatherservice.dao;

import com.globant.openweatherservice.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long>{
}
