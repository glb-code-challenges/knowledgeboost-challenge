package com.weather.weather.dao;

import com.weather.weather.entities.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogDAO extends JpaRepository<ErrorLog,Long> {
}
