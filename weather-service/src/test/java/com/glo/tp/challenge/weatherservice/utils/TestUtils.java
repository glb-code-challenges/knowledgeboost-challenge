package com.glo.tp.challenge.weatherservice.utils;

import com.glo.tp.challenge.weatherservice.domain.WeatherHistory;
import com.glo.tp.challenge.weatherservice.dto.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;

public class TestUtils {

    public static String getJsonStringFromFile(String fileName) throws IOException {
        final Resource resource = new ClassPathResource(fileName);
        return new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
    }

    public static CityDTO buildCityDTO() {
        return CityDTO.builder()
                .id(4273857L)
                .name("Kansas")
                .cod(200)
                .base("stations")
                .visibility(10000)
                .dt(1675267108)
                .coord(buildCoordDTO())
                .weather(Collections.singletonList(buildWeatherDTO()))
                .main(buildMainDTO())
                .wind(buildWindDTO())
                .clouds(buildCloudsDTO())
                .sys(buildSysDTO())
                .timeZone(-21600)
                .build();
    }

    private static CoordDTO buildCoordDTO() {
        return CoordDTO.builder()
                .lon(-98.5006f)
                .lat(38.5003f)
                .build();
    }

    private static WeatherDTO buildWeatherDTO() {
        return WeatherDTO.builder()
                .id(801)
                .main("Clouds")
                .description("few clouds")
                .icon("02d")
                .build();
    }

    private static MainDTO buildMainDTO() {
        return MainDTO.builder()
                .temp(266.44)
                .pressure(1028)
                .humidity(28)
                .feelsKile(260.14)
                .tempMin(261.3)
                .tempMax(268.29)
                .seaLevel(1028)
                .grndLevel(961)
                .build();
    }

    private static WindDTO buildWindDTO() {
        return WindDTO.builder()
                .speed(4.6)
                .deg(209)
                .gust(6.41)
                .build();
    }

    private static CloudsDTO buildCloudsDTO() {
        return CloudsDTO.builder()
                .all("23")
                .build();
    }

    private static SysDTO buildSysDTO() {
        return SysDTO.builder()
                .type(2)
                .id(2008008)
                .country("US")
                .sunrise(1675258778)
                .sunset(1675295724)
                .build();
    }

    public static WeatherHistory buildWeatherEntityObject() {
        return WeatherHistory.builder()
                .id(1L)
                .cityName("Kansas")
                .latitude(38.5003f)
                .longitude(-98.5006f)
                .code(200)
                .message("Ok")
                .operationDate(LocalDate.now())
                .build();
    }

}
