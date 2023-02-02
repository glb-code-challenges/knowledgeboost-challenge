package com.weather.weather.services;

import com.weather.weather.dao.ErrorLogDAO;
import com.weather.weather.dao.WeatherResponseDAO;
import com.weather.weather.entities.ErrorLog;
import com.weather.weather.entities.RequestWeather;
import com.weather.weather.entities.ResponseWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherServiceI{

    @Autowired
    private WeatherResponseDAO weatherResponseDAO;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${open.weather.map.key}")
    private String key;

    public ResponseWeather consultWeather(RequestWeather requestWeather)throws HttpStatusCodeException{
        switch(requestWeather.getTypeRequest()){
            case CITYNAME:
                return consultCityWeather(requestWeather);
            case LATLONG:
                return consultLatLongWeather(requestWeather);
            default:
                return null;
        }
    }

    private ResponseWeather consultCityWeather(RequestWeather requestWeather)throws HttpStatusCodeException{
        ResponseEntity<ResponseWeather> responseEntity = restTemplate.getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?q={CityName}&appid={appid}",ResponseWeather.class,
                requestWeather.getCityName(),key);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            ResponseWeather rw = responseEntity.getBody();
            rw.setIdOperacion(requestWeather.getIdOperacion());
            return weatherResponseDAO.save(rw);
        }
        return responseEntity.getBody();
    }

    private ResponseWeather consultLatLongWeather(RequestWeather requestWeather) throws HttpStatusCodeException{
        ResponseEntity<ResponseWeather> responseEntity = restTemplate.getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={appid}",
                ResponseWeather.class, requestWeather.getLatitude(),
                requestWeather.getLongitude(), key);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ResponseWeather rw = responseEntity.getBody();
            rw.setIdOperacion(requestWeather.getIdOperacion());
            return weatherResponseDAO.save(rw);
        }
        return null;
    }
}
