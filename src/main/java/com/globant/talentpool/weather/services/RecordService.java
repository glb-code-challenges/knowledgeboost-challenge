package com.globant.talentpool.weather.services;

import com.globant.talentpool.weather.models.dtos.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RecordService {
    public ResponseEntity<ResponseDTO> saveByCity(String cityName);
    public ResponseEntity<ResponseDTO> saveByLatLong(Double lat, Double lon);
}
