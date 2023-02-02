package com.weather.weather.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weather.controllers.WeatherController;
import com.weather.weather.dao.ErrorLogDAO;
import com.weather.weather.dao.WeatherRequestDAO;
import com.weather.weather.dao.WeatherResponseDAO;
import com.weather.weather.entities.RequestWeather;
import com.weather.weather.entities.ResponseWeather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WeatherController.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ErrorLogDAO errorLogDAO;
    @MockBean
    private WeatherRequestDAO weatherRequestDAO;
    @MockBean
    private WeatherResponseDAO weatherResponseDAO;
    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCityName()throws Exception {
        ResponseEntity  rp = new ResponseEntity(new ResponseWeather(), HttpStatus.OK);
        RequestWeather rw = getRequestWeatherCityName();
        when(weatherRequestDAO.save(any(RequestWeather.class))).thenReturn(rw);
        when(weatherResponseDAO.save(any(ResponseWeather.class))).thenReturn(new ResponseWeather());
        when(restTemplate.getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?q={CityName}&appid={appid}",ResponseWeather.class,
                rw.getCityName(),"36a54b35a65f47bc9e91590b0cb02b5f")).thenReturn(rp);
        mockMvc.perform(get("/weather/city/cancun")).andExpect(status().isOk());
    }

    @Test
    public void testLatLong()throws Exception {
        ResponseEntity  rp = new ResponseEntity(new ResponseWeather(), HttpStatus.OK);
        RequestWeather rw = getRequestWeatherLatLong();
        when(weatherRequestDAO.save(any(RequestWeather.class))).thenReturn(rw);
        when(weatherResponseDAO.save(any(ResponseWeather.class))).thenReturn(new ResponseWeather());
        when(restTemplate.getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={appid}",
                ResponseWeather.class, rw.getLatitude(),
                rw.getLongitude(),"36a54b35a65f47bc9e91590b0cb02b5f")).thenReturn(rp);
        mockMvc.perform(get("/weather/city/cancun")).andExpect(status().isOk());
    }

    private ResponseWeather getResponseWeather(){
        ResponseWeather rw = new ResponseWeather();
        return rw;
    }

    private RequestWeather getRequestWeatherCityName(){
        RequestWeather rw = new RequestWeather();
        rw.setId(1L);
        rw.setCityName("cancun");
        rw.setTypeRequest(RequestWeather.TypeRequest.CITYNAME);
        return rw;
    }

    private RequestWeather getRequestWeatherLatLong(){
        RequestWeather rw = new RequestWeather();
        rw.setId(1L);
        rw.setLatitude("20.9667");
        rw.setLongitude("-89.6167");
        rw.setTypeRequest(RequestWeather.TypeRequest.LATLONG);
        return rw;
    }
}
