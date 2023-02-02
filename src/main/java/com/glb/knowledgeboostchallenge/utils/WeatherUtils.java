package com.glb.knowledgeboostchallenge.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glb.knowledgeboostchallenge.api.model.Clouds;
import com.glb.knowledgeboostchallenge.api.model.Coordinates;
import com.glb.knowledgeboostchallenge.api.model.Main;
import com.glb.knowledgeboostchallenge.api.model.OpenWeatherResponse;
import com.glb.knowledgeboostchallenge.api.model.Rain;
import com.glb.knowledgeboostchallenge.api.model.Snow;
import com.glb.knowledgeboostchallenge.api.model.Sys;
import com.glb.knowledgeboostchallenge.api.model.Weather;
import com.glb.knowledgeboostchallenge.api.model.Wind;
import com.glb.knowledgeboostchallenge.dto.ConditionsData;
import com.glb.knowledgeboostchallenge.dto.Temperature;
import com.glb.knowledgeboostchallenge.dto.WeatherCondition;
import com.glb.knowledgeboostchallenge.dto.WeatherResponse;
import com.glb.knowledgeboostchallenge.exception.ConvertApiResponseException;
import com.glb.knowledgeboostchallenge.model.ResponseLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import static org.apache.logging.log4j.util.Strings.*;

public final class WeatherUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private WeatherUtils() {
    }

    public static ResponseLog createResponseLog(OpenWeatherResponse openWeatherResponse, String responseBody) {
        return ResponseLog.builder()
            .callDateTime(LocalDateTime.now())
            .responseCode(openWeatherResponse.getCod())
            .errorCause(openWeatherResponse.getMessage())
            .cityName(openWeatherResponse.getName())
            .responseJson(responseBody)
            .build();
    }

    public static OpenWeatherResponse createWeatherResponseWithException(Exception ex, HttpStatus status) {
        LOGGER.error(ex.getMessage());
        return OpenWeatherResponse.builder()
            .cod(status.value())
            .message(ex.getMessage())
            .build();
    }

    public static OpenWeatherResponse createWeatherResponseWithException(Exception ex, String message, HttpStatus status) {
        LOGGER.error(ex.getMessage());
        return OpenWeatherResponse.builder()
            .cod(status.value())
            .message(message)
            .build();
    }

    public static OpenWeatherResponse getResponseApi(String responseBody) throws ConvertApiResponseException {
        OpenWeatherResponse openWeatherResponse = null;
        try {
            openWeatherResponse = objectMapper.readValue(responseBody, OpenWeatherResponse.class);
        } catch (IOException e) {
            LOGGER.error("Error converting http response body to java object response");
            throw new ConvertApiResponseException(e.getMessage());
        }
        return openWeatherResponse;
    }

    public static WeatherResponse convertWeatherResponse(OpenWeatherResponse apiResponse) {
        if (!isEmpty(apiResponse.getMessage())) {
            return WeatherResponse.builder()
                .code(apiResponse.getCod())
                .message(apiResponse.getMessage())
                .build();
        }

        return WeatherResponse.builder()
            .id(apiResponse.getId())
            .name(apiResponse.getName())
            .countryCode(Optional.ofNullable(apiResponse.getSys())
                .map(Sys::getCountry)
                .orElse(null))
            .timezone(apiResponse.getTimezone())
            .latitude(Optional.ofNullable(apiResponse.getCoord())
                .map(Coordinates::getLat)
                .orElse(null))
            .longitude(Optional.ofNullable(apiResponse.getCoord())
                .map(Coordinates::getLon)
                .orElse(null))
            .weatherConditions(getWeatherConditions(apiResponse.getWeather()))
            .temperature(getTemperature(apiResponse.getMain()))
            .visibility(apiResponse.getVisibility())
            .conditionsData(getConditionsData(apiResponse))
            .cloudiness(Optional.ofNullable(apiResponse.getClouds())
                .map(Clouds::getAll)
                .orElse(null))
            .dataTimeCalculation(apiResponse.getDt())
            .sunriseTime(Optional.ofNullable(apiResponse.getSys())
                .map(Sys::getSunrise)
                .orElse(null))
            .sunsetTime(Optional.ofNullable(apiResponse.getSys())
                .map(Sys::getSunset)
                .orElse(null))
            .code(apiResponse.getCod())
            .message(apiResponse.getMessage())
            .build();
    }

    public static List<WeatherCondition> getWeatherConditions(List<Weather> weather) {
        if (weather == null || weather.isEmpty()) {
            return null;
        }
        return weather.stream().map(w ->
            WeatherCondition.builder()
                .conditionType(w.getMain())
                .idContidion(w.getId())
                .description(w.getDescription())
                .build()
        ).toList();
    }

    public static Temperature getTemperature(Main main) {
        if (main == null) {
            return null;
        }
        return Temperature.builder()
            .temperature(main.getTemp())
            .perception(main.getFeels_like())
            .minimumTemperature(main.getTemp_min())
            .maximumTemperature(main.getTemp_max())
            .atmosphericPressure(main.getPressure())
            .humidity(main.getHumidity())
            .seaLevel(main.getSea_level())
            .groundLevel(main.getGrnd_level())
            .build();
    }

    public static ConditionsData getConditionsData(OpenWeatherResponse response) {
        ConditionsData data = new ConditionsData();
        Optional.ofNullable(response.getWind())
            .map(Wind::getSpeed)
            .ifPresent(data::setWindSpeed);
        Optional.ofNullable(response.getWind())
            .map(Wind::getDeg)
            .ifPresent(data::setWindDirection);
        Optional.ofNullable(response.getWind())
            .map(Wind::getGust)
            .ifPresent(data::setWindGust);
        Optional.ofNullable(response.getRain())
            .map(Rain::getRainVolumeOneHr)
            .ifPresent(data::setRainVolumeOneHr);
        Optional.ofNullable(response.getRain())
            .map(Rain::getRainVolumeThreeHr)
            .ifPresent(data::setRainVolumeThreeHr);
        Optional.ofNullable(response.getSnow())
            .map(Snow::getSnowVolumeOneHr)
            .ifPresent(data::setSnowVolumeOneHr);
        Optional.ofNullable(response.getSnow())
            .map(Snow::getSnowVolumeThreeHr)
            .ifPresent(data::setSnowVolumeThreeHr);
        return data;
    }

}
