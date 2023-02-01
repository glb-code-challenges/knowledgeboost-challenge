package com.globant.weather.service;

import com.globant.weather.dto.WeatherResponse;
import com.globant.weather.entity.Weather;
import com.globant.weather.exeption.WeatherException;
import com.globant.weather.repository.IWeatherRepository;
import com.globant.weather.utility.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class WeatherServiceImp implements IWeatherService {

    private static final Logger logger = LogManager.getLogger(WeatherServiceImp.class);

    @Value("${weather.config.url}")
    private String url;
    @Value("${weather.config.appid}")
    private String appId;

    private RestTemplate restTemplate;
    private IWeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImp(IWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public WeatherResponse getByCityName(String cityName) throws WeatherException {
        WeatherResponse weateWeatherResponse = new WeatherResponse();
        try {
            String finalUrl = this.url + "?APPID={APPID}&q={q}";
            Map<String, String> params = Map.of("APPID", this.appId, "q", cityName);
            ResponseEntity<WeatherResponse> response = this.restTemplate.getForEntity(finalUrl, WeatherResponse.class, params);
            if (response.getStatusCode().is2xxSuccessful()) {
                weateWeatherResponse = response.getBody();
                weatherRepository.save(new Weather(response.getStatusCode().value(), "OK", weateWeatherResponse.getName(), LocalDateTime.now()));
            }
        } catch (HttpClientErrorException e) {
            weatherRepository.save(Utils.getWeatherEntityFromApi(e));
            logger.error(e.getMessage());
            Utils.Error(e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Utils.Error(e);
        }
        return weateWeatherResponse;
    }

    @Override
    public WeatherResponse getByCoordinates(String latitude, String longitude) throws WeatherException {
        WeatherResponse weateWeatherResponse = new WeatherResponse();
        try {
            String finalUrl = this.url + "?APPID={APPID}&lat={lat}&lon={lon}";
            Map<String, String> params = Map.of("APPID", this.appId, "lat", latitude, "lon", longitude);
            ResponseEntity<WeatherResponse> response = this.restTemplate.getForEntity(finalUrl, WeatherResponse.class, params);
            if (response.getStatusCode().is2xxSuccessful()) {
                weateWeatherResponse = response.getBody();
                weatherRepository.save(new Weather(response.getStatusCode().value(), "OK", weateWeatherResponse.getName(), LocalDateTime.now()));
            }
        } catch (HttpClientErrorException e) {
            weatherRepository.save(Utils.getWeatherEntityFromApi(e));
            logger.error(e.getMessage());
            Utils.Error(e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Utils.Error(e);
        }
        return weateWeatherResponse;
    }
}
