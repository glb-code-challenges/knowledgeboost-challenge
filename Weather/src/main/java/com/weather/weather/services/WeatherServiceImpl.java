package com.weather.weather.services;

import com.weather.weather.dao.ErrorLogDAO;
import com.weather.weather.dao.WeatherRequestDAO;
import com.weather.weather.entities.ErrorLog;
import com.weather.weather.entities.RequestWeather;
import com.weather.weather.entities.ResponseWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class WeatherServiceImpl implements  WeatherServiceI {

    @Autowired
    private WeatherRequestDAO weatherRequestDAO;
    @Autowired
    private OpenWeatherServiceI openWeatherService;
    @Autowired
    private ErrorLogDAO errorLogDAO;

    @Override
    public RequestWeather createRequestWeather(String cityName) {
        RequestWeather requestWeather = new RequestWeather();
        requestWeather.setCityName(cityName);
        requestWeather.setTypeRequest(RequestWeather.TypeRequest.CITYNAME);
        return weatherRequestDAO.save(requestWeather);
    }

    @Override
    public RequestWeather createRequestWeather(String latitude, String longitude) {
        RequestWeather requestWeather = new RequestWeather();
        requestWeather.setTypeRequest(RequestWeather.TypeRequest.LATLONG);
        requestWeather.setLongitude(longitude);
        requestWeather.setLatitude(latitude);
        return weatherRequestDAO.save(requestWeather);
    }

    @Override
    public Object consultWeather(RequestWeather requestWeather){
        try {
            return openWeatherService.consultWeather(requestWeather);
        }catch( HttpStatusCodeException error) {
            ErrorLog log = new ErrorLog();
            log.setIdOperacion(requestWeather.getIdOperacion());
            log.setStatusCode(error.getStatusCode().value());
            log.setError(error.getMessage());
            log = errorLogDAO.save(log);
            return log;
        }
    }
}
