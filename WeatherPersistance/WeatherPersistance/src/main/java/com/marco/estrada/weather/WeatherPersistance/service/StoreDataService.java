package com.marco.estrada.weather.WeatherPersistance.service;

import com.marco.estrada.weather.WeatherPersistance.dto.Request;
import com.marco.estrada.weather.WeatherPersistance.entity.Error;
import com.marco.estrada.weather.WeatherPersistance.entity.RequestData;
import com.marco.estrada.weather.WeatherPersistance.repository.ErrorRepository;
import com.marco.estrada.weather.WeatherPersistance.repository.RequestDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreDataService {

    private final RequestDataRepository requestDataRepository;

    private final ErrorRepository errorRepository;



    @Transactional
    public void storeRequestData(Request data) {

        log.info("Saving request data");
        RequestData requestData = RequestData.builder()
                .cityName(data.getCityName())
                .responseCode(data.getStatusCode())
                .operationDate(data.getDate())
                .build();

        requestDataRepository.save(requestData);

        log.info("data saved");

        if (Objects.nonNull(data.getErrorDetail())) {
            log.info("saving error data");
            errorRepository.save(Error.builder()
                .description(data.getErrorDetail().getDescription())
                .requestData(requestData)
                .build());
            log.info("error data saved");
        }

    }

}
