package com.globant.challenge.weather.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.challenge.weather.model.entity.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByName(String username);

}
