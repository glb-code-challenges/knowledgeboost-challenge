package com.aforo255.appdeposit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional("mongoTransactionManager") // This doesn't work due to installed mongo is not a replica set.
public class WeatherControllerTests {
	
	@Autowired
    private MockMvc mvc;
	
	
	@Test
	@Order(1)
	public void canGetByLatLonWhenExists() throws Exception {
		MockHttpServletResponse response = mvc.perform(get("/weather/1/1"))
                .andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	@Order(2)
	public void canGetByCityNameWhenExists() throws Exception {
		MockHttpServletResponse response = mvc.perform(get("/weather/paris"))
                .andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	@Order(3)
	public void canGetByCityNameWhenNoExists() throws Exception {
		MockHttpServletResponse response = mvc.perform(get("/weather/cdmx"))
                .andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	@Order(4)
	public void canGetByLatLonWrongLat() throws Exception {
		MockHttpServletResponse response = mvc.perform(get("/weather/500/1"))
                .andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_GATEWAY.value());
	}

}
