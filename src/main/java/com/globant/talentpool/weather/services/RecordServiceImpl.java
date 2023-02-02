package com.globant.talentpool.weather.services;

import com.globant.talentpool.weather.models.RecordEntity;
import com.globant.talentpool.weather.models.WeatherModel;
import com.globant.talentpool.weather.models.dtos.ResponseDTO;
import com.globant.talentpool.weather.repositories.RecordRepository;

import com.globant.talentpool.weather.utils.AppConstants;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.*;

import java.util.Date;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordRepository recordRepository;

    WeatherApiService weatherApiService;

    public RecordServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApiService = retrofit.create(WeatherApiService.class);
    }

    @Override
    public ResponseEntity<ResponseDTO> saveByCity(String cityName) {
        log.info("m=saveByCity ::: Entering saveByCity method. City to evaluate: " + cityName);
        if (cityName.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDTO.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message("City name is required")
                            .build());


        try {
            Response<WeatherModel> response = weatherApiService.requestWeatherByCity(cityName).execute();
            String responseMessage = "";

            if (response.isSuccessful()){
                responseMessage = response.message();

            }else{
                Gson g = new Gson();
                ResponseDTO errorResponse = g.fromJson(response.errorBody().string(), ResponseDTO.class);
                responseMessage = errorResponse.getMessage();
            }

            recordRepository.save(RecordEntity.builder()
                    .recordCity(cityName)
                    .recordStatusCode(response.code())
                    .recordTimestamp(new Date())
                    .recordBodyResponse(responseMessage)
                    .build());

                return ResponseEntity.status(HttpStatus.valueOf(response.code())).body(
                        ResponseDTO.builder()
                                .code(response.code())
                                .message(responseMessage)
                                .build()
                );


        }catch (Exception ex){
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseDTO.builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("Internal Server Error: " + ex.getMessage())
                            .build()
            );
        }
    }




    @Override
    public ResponseEntity<ResponseDTO> saveByLatLong(Double lat, Double lon) {
        log.info("m=saveByCity ::: Entering saveByCity method. Coords to evaluate: " + lat + ", " + lon);


        try {
            Response<WeatherModel> response = weatherApiService.requestWeatherByLatLon(lat, lon).execute();
            String responseMessage = "";
            if (response.isSuccessful()) {
                responseMessage = response.message();
            } else {
                Gson g = new Gson();
                ResponseDTO errorResponse = g.fromJson(response.errorBody().string(), ResponseDTO.class);
                responseMessage = errorResponse.getMessage();
            }

            recordRepository.save(RecordEntity.builder()
                    .recordCity(response.body() == null || response.body().getName().isEmpty() ?
                            "N/D" : response.body().getName())
                    .recordStatusCode(response.code())
                    .recordTimestamp(new Date())
                    .recordBodyResponse(responseMessage)
                    .build());

            return ResponseEntity.status(HttpStatus.valueOf(response.code())).body(
                    ResponseDTO.builder()
                            .code(response.code())
                            .message(responseMessage)
                            .build()
            );


        } catch (Exception ex) {
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseDTO.builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("Internal Server Error: " + ex.getMessage())
                            .build()
            );
        }
    }
}
