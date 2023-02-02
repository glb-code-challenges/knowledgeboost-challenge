package com.glb.knowledgeboostchallenge.exception;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.glb.knowledgeboostchallenge.api.model.OpenWeatherResponse;
import com.glb.knowledgeboostchallenge.utils.WeatherUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.glb.knowledgeboostchallenge.utils.Constants.*;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseWeatherException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public OpenWeatherResponse handleWeatherClientException(BaseWeatherException ex) {
        return WeatherUtils.createWeatherResponseWithException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public OpenWeatherResponse handleDatabaseException(DataBaseException ex) {
        return WeatherUtils.createWeatherResponseWithException(ex
            , MESSAGE_DATABASE_UNAVAILABLE
            , HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public OpenWeatherResponse handleAuthenticationException(AuthenticationException ex, HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return WeatherUtils.createWeatherResponseWithException(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public OpenWeatherResponse handleRequestFieldsException(ConstraintViolationException ex, HttpServletResponse response){
        return WeatherUtils.createWeatherResponseWithException(ex, HttpStatus.resolve(response.getStatus()));
    }
}
