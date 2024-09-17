package com.example.group.demoArtifact01.student.openweather;

import com.example.group.demoArtifact01.student.City;
import com.example.group.demoArtifact01.student.CityRepository;
import com.example.group.demoArtifact01.student.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.disposables.CompositeDisposable;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Data
public class WeatherService {

    private  CityRepository cityRepository;

    @Autowired(required=false)
    public WeatherService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    //https://stackoverflow.com/questions/58759133/openweathermap-api-java

    /*https://gist.github.com/iamnaran/cb2e8a03e302a697687d1c724dbf5512*/

    private static final String TAG = WeatherService.class.getSimpleName();
    CompositeDisposable compositeDisposable;
    /*WeakReference<View> view;*/
    IView view;
    Map<String, String> options = new HashMap();

    @Autowired(required=false)
    public WeatherService(IView view) {
        /*this.view = new WeakReference<>(view);*/
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
        //this.cityRepository = cityRepository;
    }







    public void getWeatherDetails(double latitude, double longitude, String appid) {
        System.out.println("accessing method getWeatherDetails");

        String url = "http://api.openweathermap.org/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) //This is the only mandatory call on Builder object.
                .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                .build();


        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);

        weatherApiService.requestWeather(String.valueOf(latitude), String.valueOf(longitude), "metric", "10").enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                System.out.println("accesing method onResponse");


                if (response.isSuccessful()) {
                    System.out.println("response successful");

                    if (response.body() != null) {
                        System.out.println("response.body() not null");
                        System.out.println("body: " + response.body());
                        if (getView() != null) {
                            System.out.println("getView() not null");

                            getView().onWeatherApiSuccess(response.body());
                        }


                    }

                }

            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                System.out.println("response failure");
                if (getView() != null) {
                    System.out.println("getView() not null");

                    getView().onWeatherApiFailure(String.valueOf(t.getMessage()));
                }

            }
        }
        );





    }

    public void insertWeatherDetails(double latitude, double longitude) {
        System.out.println("accessing method insertWeatherDetails");

        String url = "http://api.openweathermap.org/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) //This is the only mandatory call on Builder object.
                .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                .build();


        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);

        City city = new City();
        weatherApiService.requestWeather(String.valueOf(latitude), String.valueOf(longitude), "metric", "10").enqueue(new Callback<WeatherModel>() {
                                                                                                                          @Override
                                                                                                                          public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                                                                                                                              System.out.println("accesing method onResponse");


                                                                                                                              if (response.isSuccessful()) {
                                                                                                                                  System.out.println("response successful");

                                                                                                                                  if (response.body() != null) {
                                                                                                                                      System.out.println("response.body() not null");
                                                                                                                                      /*System.out.println("body: " + response.body());*/
                                                                                                                                      WeatherModel wm = response.body();
                                                                                                                                      System.out.println("body: " + wm);



                                                                                                                                      city.setName(wm.getCity().getName());
                                                                                                                                      city.setLat(wm.getCity().getCoord().getLat());
                                                                                                                                      city.setLon(wm.getCity().getCoord().getLon());
                                                                                                                                      city.setCountry(wm.getCity().getCountry());


                                                                                                                                      if (getView() != null) {
                                                                                                                                          System.out.println("getView() not null");

                                                                                                                                          getView().onWeatherApiSuccess(response.body());
                                                                                                                                      }


                                                                                                                                  }

                                                                                                                              }

                                                                                                                          }

                                                                                                                          @Override
                                                                                                                          public void onFailure(Call<WeatherModel> call, Throwable t) {
                                                                                                                              System.out.println("response failure");
                                                                                                                              if (getView() != null) {
                                                                                                                                  System.out.println("getView() not null");

                                                                                                                                  getView().onWeatherApiFailure(String.valueOf(t.getMessage()));
                                                                                                                              }

                                                                                                                          }
                                                                                                                      }
        );
        cityRepository.save(city);





    }



    private IView getView() throws NullPointerException {
        if (view != null)
            //return view.get();
            return null;

        else
            throw new NullPointerException("View is unavailable");
    }


    /*public interface View {

        void onWeatherApiSuccess(WeatherModel weatherData);

        void onWeatherApiFailure(String message);


    }*/

}
