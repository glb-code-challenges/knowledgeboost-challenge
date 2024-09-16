package com.weather.api.repository;

import com.weather.api.entity.ExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionsRepository extends JpaRepository<ExecutionEntity, Long> {
}
