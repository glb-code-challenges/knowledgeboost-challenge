package com.globant.talentpool.weather.controller;

import com.globant.talentpool.weather.controllers.RecordController;
import com.globant.talentpool.weather.models.dtos.ResponseDTO;
import com.globant.talentpool.weather.services.RecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecordControllerTest {

    @Mock
    private RecordService recordService;
    @InjectMocks
    private RecordController recordController;

    @Test
    public void saveByCityTestOK(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(200)
                .message("Record saved!")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByCity("Pachuca")).thenReturn(responseEntity);
        assertTrue(recordController.saveByCity("Pachuca").equals(responseEntity));
    }

    @Test
    public void saveByCityTestNotFound(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(404)
                .message("City not found!")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByCity("Lololol")).thenReturn(responseEntity);
        assertTrue(recordController.saveByCity("Lololol").equals(responseEntity));
    }

    @Test
    public void saveByCityTestBadRequest(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(400)
                .message("City is required")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByCity("")).thenReturn(responseEntity);
        assertTrue(recordController.saveByCity("").equals(responseEntity));
    }

    @Test
    public void saveByCityTestInternalServerError(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(500)
                .message("Internal Server Error")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByCity("+´324sdfsdfdsssssssssssssssssssssssssssssssssss" +
                "sfffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")).thenReturn(responseEntity);
        assertTrue(recordController.saveByCity("+´324sdfsdfdsssssssssssssssssssssssssssssssssss" +
                "sfffffffffffffffffffffffffffffffffffffffffffffffffffffffffff").equals(responseEntity));
    }



    @Test
    public void saveByLatLonTestOK(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(200)
                .message("Record saved!")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByLatLong(1.000, -2.00)).thenReturn(responseEntity);
        assertTrue(recordController.saveByLatLon(1.000,-2.00).equals(responseEntity));
    }

    @Test
    public void saveByLatLonTestBadRequest(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(400)
                .message("Bad request!")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByLatLong(100.0, 100.0)).thenReturn(responseEntity);
        assertTrue(recordController.saveByLatLon(100.0,100.0).equals(responseEntity));
    }

    @Test
    public void saveByLatLonTestBadRequestNone(){
        ResponseDTO responseBody = ResponseDTO.builder()
                .code(400)
                .message("Bad request!")
                .build();
        ResponseEntity responseEntity = ResponseEntity.ok().body(responseBody);
        when(recordService.saveByLatLong(null, null)).thenReturn(responseEntity);
        assertTrue(recordController.saveByLatLon(null,null).equals(responseEntity));
    }

}
