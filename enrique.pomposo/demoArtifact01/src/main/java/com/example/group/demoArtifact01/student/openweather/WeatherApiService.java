package com.example.group.demoArtifact01.student.openweather;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiService {

    @Headers("x-api-key: " + AppConstants.WEATHER_API_KEY)
    @GET("data/2.5/forecast")
    /*Call<WeatherModel> requestWeather(@Header("x-api-key") String appid, @Query("lat") String lat, @Query("lon") String lon, @Query("units") String units, @Query("cnt") String count);*/
    Call<WeatherModel> requestWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("units") String units, @Query("cnt") String count);
}
