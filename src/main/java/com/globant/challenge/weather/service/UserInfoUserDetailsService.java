package com.globant.challenge.weather.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.globant.challenge.weather.dao.UserInfoDao;
import com.globant.challenge.weather.model.entity.UserInfo;
import com.globant.challenge.weather.user.UserInfoUserDetails;

public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userDao.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

	}

}
