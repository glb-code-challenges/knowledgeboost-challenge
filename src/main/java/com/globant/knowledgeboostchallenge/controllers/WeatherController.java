package com.globant.knowledgeboostchallenge.controllers;


import com.globant.knowledgeboostchallenge.dao.entities.WeatherInfoResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/weather/")
@OpenAPIDefinition(
        info = @Info( title = "Weather Service",
                      version = "1.0.0",
                      description = "This microservice contains endpoint for weather service",
                      contact = @Contact(name = "Gerardo Salvador Bermúdez", email = "gerardo.salvador@globant.com"),
                      license = @License(name = "GLOBANT - Talent pool - Weather Challenge")
))
public interface WeatherController {


    @Tag(name = "Weather service weatherByCityNameAndAppId", description = "This service gets the weather by City And AppId ")
    @Operation( summary = "Get Weather By City And AppId",
            description ="<div class=\"renderedMarkdown\"><div><div><h1>Operation description</h1> <h2>Objectives</h2> <p>In this operation you can get the weather</p> <h2>Input data</h2> <p>CytyName<br/> AppId</p> <h2>Output Data</h2> <p>Gets weather info object</p> <h2>invoke application </h2> <p>http://localhost:8080/swagger-ui/index.html#/Weather%20service/getWeatherByLatAndLonAndAppId</p></div></div></div>"
            ,externalDocs =
                @ExternalDocumentation(
                        description = "weatherByCityNameAndAppId",
                        url = "https://api.openweathermap.org/data/2.5/weather?q=Mexico&APPID=18e9633e9829e4d1b05adf1723de2bb1"))

    @GetMapping(value = "city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherInfoResponse> getWeatherByCityAndAppId(

            @Parameter(description = "cityName.", example = "London")
            @Size(max = 20, message = "{weather.message.cityName}")
            @NotNull @NotBlank(message = "{weather.message.NotBlank.cityName}")
            @PathVariable(value ="cityName") String cityName,

            @Parameter(description = "AppID.", example = "18e9633e9829e4d1b05adf1723de2bb1")
            @Size(max = 32, message = "{weather.message.appId}")
            @NotNull @NotBlank(message = "{weather.message.NotBlank.appId}")
            @RequestParam(value ="appId") String appId);


    @Tag(name = "Weather service weatherByLatitudeAndLongitudeAndAppId", description = "This service gets the weather by Latitude Longitude AppId ")
    @Operation( summary = "Get Weather By Latitude Longitude AppId",
                description ="<div class=\"renderedMarkdown\"><div><div><h1>Operation description</h1> <h2>Objectives</h2> <p>In this operation you can get the weather</p> <h2>Input data</h2> <p>Latitude <br/> Longitude<br/> AppId</p> <h2>Output Data</h2> <p>Gets weather info object</p> <h2>invoke application </h2> <p>http://localhost:8080/swagger-ui/index.html#/Weather%20service/getWeatherByLatAndLonAndAppId</p></div></div></div>"
                ,externalDocs =
                            @ExternalDocumentation(
                                    description = "Example: OpenApi ==> weatherByLatitudeAndLongitudeAndAppId",
                                    url = "http://api.openweathermap.org/data/2.5/weather?lat=19.3584097&lon=-99.0608733&appId=18e9633e9829e4d1b05adf1723de2bb1"))
    @GetMapping(value = "latitude/{latitude}/longitude/{longitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherInfoResponse> getWeatherByLatAndLonAndAppId(

            @Parameter(description = "latitude.", example = "19.3584097")
            @Size(max = 20, message = "{weather.message.latitude}")
            @NotNull @NotBlank(message = "{weather.message.NotBlank.latitude}")
            @PathVariable(value ="latitude") String latitude,

            @Parameter(description = "longitude.", example = "-99.0608733")
            @Size(max = 20, message = "{weather.message.longitude}")
            @NotNull @NotBlank(message = "{weather.message.NotBlank.longitude}")
            @PathVariable(value = "longitude") String longitude,

            @Parameter(description = "AppID.", example = "18e9633e9829e4d1b05adf1723de2bb1")
            @Size(max = 32, message = "{weather.message.appId}")
            @NotNull @NotBlank(message = "{weather.message.NotBlank.appId}")
            @RequestParam(value = "appId") String appId);

}
