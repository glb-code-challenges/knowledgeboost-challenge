package com.globant.knowledgeboostchallenge.utils;

import com.globant.knowledgeboostchallenge.dao.dtos.WeatherInfoDto;
import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class WeatherUtil {

    private WeatherUtil() {
    }

    public static WeatherInfoResponse getWeatherInfoResponse(ResponseEntity<WeatherInfoDto> infoDtoResponseEntity){
        Gson g=new Gson();
        WeatherInfoResponse infoResponse = new WeatherInfoResponse();

        if(infoDtoResponseEntity!=null && infoDtoResponseEntity.getBody()!=null) {
            var weatherInfoDto = infoDtoResponseEntity.getBody();
            g.toJson(weatherInfoDto);
            var listWeather = infoDtoResponseEntity.getBody().getWeather();
            List<String> weatherStringList = new ArrayList<>();
            if (listWeather != null){
                    for (int i = 0; i < listWeather.size(); i++) {
                        weatherStringList.add(listWeather.get(i).getMain() + "-" + listWeather.get(i).getDescription());
                    }
            }
            infoResponse.setLongitude(weatherInfoDto.getCoord().getLon());
            infoResponse.setLatitude(weatherInfoDto.getCoord().getLat());
            infoResponse.setWeather(weatherStringList);
            infoResponse.setTemp(getDegrees(weatherInfoDto.getMain().getTemp()));
            infoResponse.setTempMin(getDegrees(weatherInfoDto.getMain().getTemp_min()));
            infoResponse.setTempMax(getDegrees(weatherInfoDto.getMain().getTemp_max()));
            infoResponse.setPressure(weatherInfoDto.getMain().getPressure());
            infoResponse.setHumidity(weatherInfoDto.getMain().getHumidity());
            infoResponse.setSunrise(Timestamp.from(Instant.ofEpochSecond(weatherInfoDto.getSys().getSunrise())));
            infoResponse.setSunset(Timestamp.from(Instant.ofEpochSecond(weatherInfoDto.getSys().getSunset())));
            infoResponse.setResponseCode(weatherInfoDto.getCod());
            infoResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
            infoResponse.setCityName(weatherInfoDto.getSys().getCountry() + "-" + weatherInfoDto.getName());
            infoResponse.setErrorCause(null);

            g.toJson(infoResponse);
        }
        return infoResponse;
    }

    private static String getDegrees(double degree){
        DecimalFormat df = new DecimalFormat("#.00");
        Integer kelvin =273;
        return Double.valueOf(df.format(degree - kelvin)) + " °C";
    }
}
