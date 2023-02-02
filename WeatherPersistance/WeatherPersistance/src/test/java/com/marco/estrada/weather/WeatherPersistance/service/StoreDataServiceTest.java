package com.marco.estrada.weather.WeatherPersistance.service;

import com.marco.estrada.weather.WeatherPersistance.dto.ErrorDetail;
import com.marco.estrada.weather.WeatherPersistance.dto.Request;
import com.marco.estrada.weather.WeatherPersistance.entity.Error;
import com.marco.estrada.weather.WeatherPersistance.entity.RequestData;
import com.marco.estrada.weather.WeatherPersistance.repository.ErrorRepository;
import com.marco.estrada.weather.WeatherPersistance.repository.RequestDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class StoreDataServiceTest {

    @InjectMocks
    StoreDataService service;

    @Mock
    ErrorRepository errorRepository;
    
    @Mock
    RequestDataRepository requestDataRepository;

    @Test
    void storeRequestDataWithError() {

        service.storeRequestData(Request.builder()
                .cityName("London")
                .date(new Date())
                .statusCode(500)
                .errorDetail(ErrorDetail.builder()
                        .description("Error test ")
                        .build())
                .build());
        verify(errorRepository, times(1)).save(any(Error.class));
        verify(requestDataRepository, times(1)).save(any(RequestData.class));
    }

    @Test
    void storeRequestData() {

        service.storeRequestData(Request.builder()
            .cityName("London")
            .date(new Date())
            .statusCode(500)
            .build());
        verify(errorRepository, times(0)).save(any(Error.class));
        verify(requestDataRepository, times(1)).save(any(RequestData.class));
    }
}