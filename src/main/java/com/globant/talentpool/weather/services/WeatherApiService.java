package com.globant.talentpool.weather.services;

import com.globant.talentpool.weather.models.WeatherModel;
import com.globant.talentpool.weather.utils.AppConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiService {
    @Headers("x-api-key: " + AppConstants.API_KEY)
    @GET("data/2.5/weather")
    Call<WeatherModel> requestWeatherByCity(@Query("q") String cityName);

    @Headers("x-api-key: " + AppConstants.API_KEY)
    @GET("data/2.5/weather")
    Call<WeatherModel> requestWeatherByLatLon(@Query("lat") Double lat, @Query("lon") Double lon);
}
