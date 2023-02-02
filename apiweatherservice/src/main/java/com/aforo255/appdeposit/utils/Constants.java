package com.aforo255.appdeposit.utils;

public class Constants {

	public static final String OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
	public static final Object APP_ID = "7d1ca623d0ff765085f43b66d3e774a8";
	public static final String REGEX_LATITUDE = "^-?([0-8]?[0-9]|90)(\\.[0-9]{1,18})$";
	public static final String REGEX_LONGITUDE = "^-?([0-9]{1,2}|1[0-7][0-9]|180)(\\.[0-9]{1,18})?$";
	
	public static final Integer HTTP_CODE_NOT_FOUND = 404;
	public static final Integer HTTP_CODE_BAD_GATEWAY = 502;
	public static final Integer HTTP_CODE_GENERAL_SERVER_ERROR = 500;
	
}
