package com.globant.challenge.weather.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.globant.challenge.weather.dao.UserInfoDao;
import com.globant.challenge.weather.model.entity.UserInfo;

@SpringBootTest
public class UserInfoUserDetailsServiceTest {

	@Autowired
	UserInfoUserDetailsService userDetailsService;

	@MockBean
	UserInfoDao userDao;

	@Test
	void loadUserByUsername() {

		// given
		var id = UUID.randomUUID();
		var userName = "name.test";
		var email = "name.test@globant.com";
		var password = "231312414";
		var roles = "ADMIN";
		var userInfo = Optional
				.of(UserInfo.builder().id(id).name(userName).email(email).password(password).roles(roles).build());

		// when
		when(userDao.findByName(anyString())).thenReturn(userInfo);
		var response = userDetailsService.loadUserByUsername(userName);

		// then
		assertNotNull(response);

	}

	@Test
	void loadUserByUsernameWhenUserNotFound() {

		// given
		var userName = "name.test";
		;
		Optional<UserInfo> userInfo = Optional.empty();

		// when
		when(userDao.findByName(anyString())).thenReturn(userInfo);
		var e = assertThrows(UsernameNotFoundException.class, () -> {
			userDetailsService.loadUserByUsername(userName);
		});

		// then
		assertNotNull(e);

	}
}
