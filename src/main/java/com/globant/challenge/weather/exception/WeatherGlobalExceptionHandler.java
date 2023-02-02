package com.globant.challenge.weather.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globant.challenge.weather.dao.IWeatherRequestLogDao;
import com.globant.challenge.weather.error.WeatherApiError;
import com.globant.challenge.weather.model.entity.WeatherRequestLog;

@ControllerAdvice
public class WeatherGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private IWeatherRequestLogDao weatherRequestLogDao;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();

		WeatherApiError apiError = WeatherApiError.builder().status(HttpStatus.valueOf(status.value()))
				.message(ex.getLocalizedMessage()).path(requestPath).build();

		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName("ERROR").responseCode(status.value())
				.responseMessage(ex.getMessage()).build());

		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		
		WeatherApiError apiError = WeatherApiError.builder().status(HttpStatus.valueOf(status.value()))
				.message(ex.getLocalizedMessage()).path(requestPath).build();

		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName("ERROR").responseCode(status.hashCode())
				.responseMessage(ex.getMessage()).build());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		
		WeatherApiError apiError = WeatherApiError.builder().status(HttpStatus.valueOf(status.value()))
				.message(ex.getLocalizedMessage()).path(requestPath).build();

		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName("ERROR").responseCode(status.value())
				.responseMessage(ex.getMessage()).build());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		
		WeatherApiError apiError = WeatherApiError.builder().status(HttpStatus.valueOf(status.value()))
				.message(ex.getLocalizedMessage()).path(requestPath).build();

		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName("ERROR").responseCode(status.value())
				.responseMessage(ex.getMessage()).build());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ AuthenticationException.class, Unauthorized.class })
	@ResponseBody
	public ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) {
		
		String requestPath = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		
		WeatherApiError apiError = WeatherApiError.builder().status(HttpStatus.UNAUTHORIZED)
				.message(ex.getLocalizedMessage()).path(requestPath).build();

		weatherRequestLogDao.save(WeatherRequestLog.builder().cityName("ERROR")
				.responseCode(HttpStatus.UNAUTHORIZED.value()).responseMessage(ex.getMessage()).build());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
