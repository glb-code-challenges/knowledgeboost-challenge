package com.aforo255.appdeposit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.aforo255.appdeposit.client.dto.OpenWeatherResponse;
import com.aforo255.appdeposit.model.WeatherModel;
import com.aforo255.appdeposit.repository.IWeatherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
//@Transactional("mongoTransactionManager") // This doesn't work due to installed mongo is not a replica set.
public class WeatherRepositoryTests {
	
	@Autowired
    private IWeatherRepository weatherRepository;
	
	@Test
	@Order(1)
	public void canInsert() throws Exception {
		String record = "{\"cod\": \"200\",\"message\": null,\"name\": \"Paris\",\"id\": \"2988507\",\"timezone\": \"3600\",\"sys\": {\"sunset\": \"1675356505\",\"sunrise\": \"1675322401\"},\"dt\": \"1675300213\",\"clouds\": {\"all\": \"0\"},\"wind\": {\"speed\": \"4.12\",\"deg\": \"270\",\"gust\": null},\"visibility\": \"10000\",\"main\": {\"temp\": \"279.07\",\"pressure\": \"1029\",\"humidity\": \"85\",\"feels_like\": \"276.07\",\"temp_min\": \"277.01\",\"temp_max\": \"280.49\",\"sea_level\": null,\"grnd_level\": null},\"base\": \"stations\",\"weather\": [{\"id\": \"800\",\"main\": \"Clear\",\"description\": \"clear sky\",\"icon\": \"01n\"}],\"coord\": {\"lon\": \"2.3488\",\"lat\": \"48.8534\"}}";
		ObjectMapper om = new ObjectMapper();
		OpenWeatherResponse resp = om.readValue(record, OpenWeatherResponse.class);
		WeatherModel model = new WeatherModel();
		model.setResponse(resp);
		model.setCityName("Paris");
		model.setTimestamp(LocalDateTime.now());
		model = weatherRepository.insert(model);
		assertNotNull(model.getId());
	}

}
