package com.glb.knowledgeboostchallenge.utils;

public class Constants {

    public static final String WEATHER_API = "/weather";
    public static final String GET_BY_NAME = "/city/{cityName}";
    public static final String GET_BY_COORDINATES = "/latitude/{latitude}/longitude/{longitude}";

    public static final String OPEN_WEATHER_API_BASE = "https://api.openweathermap.org/data/2.5/weather";
    public static final String OPEN_WEATHER_BY_CITY = OPEN_WEATHER_API_BASE + "?q=%s&appid=%s";
    public static final String OPEN_WEATHER_BY_COORDINATES = OPEN_WEATHER_API_BASE + "?lat=%s&lon=%s&appid=%s";

    // Messages
    public static final String MESSAGE_DATABASE_UNAVAILABLE = "The database service is unavailable. Contact to report.";
    public static final String MESSAGE_AUTH_MISSING = "Full authentication is required to access this resource";
    public static final String MESSAGE_CITY_NOT_FOUND = "city not found";
    public static final String MESSAGE_SERVER_ERROR = "Internal Server Error";

}
