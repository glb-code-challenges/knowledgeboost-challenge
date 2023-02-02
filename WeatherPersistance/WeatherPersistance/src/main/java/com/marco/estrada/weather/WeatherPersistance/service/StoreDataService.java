package com.marco.estrada.weather.WeatherPersistance.service;

import com.marco.estrada.weather.WeatherPersistance.dto.Request;
import com.marco.estrada.weather.WeatherPersistance.entity.Error;
import com.marco.estrada.weather.WeatherPersistance.entity.RequestData;
import com.marco.estrada.weather.WeatherPersistance.repository.ErrorRepository;
import com.marco.estrada.weather.WeatherPersistance.repository.RequestDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StoreDataService {

    private final RequestDataRepository requestDataRepository;

    private final ErrorRepository errorRepository;



    @Transactional
    public void storeRequestData(Request data) {
        RequestData requestData = RequestData.builder()
                .cityName(data.getCityName())
                .responseCode(data.getStatusCode())
                .operationDate(data.getDate())
                .build();

        requestDataRepository.save(requestData);

        if (Objects.nonNull(data.getErrorDetail()))
            errorRepository.save(Error.builder()
                    .description(data.getErrorDetail().getDescription())
                    .requestData(requestData)
                    .build());

    }

}
