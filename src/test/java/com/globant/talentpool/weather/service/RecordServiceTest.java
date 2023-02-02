package com.globant.talentpool.weather.service;


import com.globant.talentpool.weather.models.dtos.ResponseDTO;
import com.globant.talentpool.weather.repositories.RecordRepository;
import com.globant.talentpool.weather.services.RecordServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class RecordServiceTest {
    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordServiceImpl recordService;

    @Test
    public void saveByCityTestOk() throws IOException {
        ResponseEntity<ResponseDTO> response = recordService.saveByCity("Pachuca");
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    public void saveByCityTestNotFound() throws IOException {
        assertTrue(HttpStatus.NOT_FOUND.equals(recordService.saveByCity("Lololol").getStatusCode()));
    }

    @Test
    public void saveByCityTestBadRequest() throws IOException {
        assertTrue(HttpStatus.BAD_REQUEST.equals(recordService.saveByCity("").getStatusCode()));
    }

    @Test
    public void saveByCityTestInternalServerError() throws IOException {
        assertFalse(HttpStatus.OK.equals(
                recordService.saveByCity("+´324sdfsdfdsssssssssssssssssssssssssssssssssss" +
                        "sfffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
                        .getStatusCode()));
    }

    @Test
    public void saveByLatLonTestOk() throws IOException {
        assertTrue(HttpStatus.OK.equals(recordService.saveByLatLong(22.00, -99.00).getStatusCode()));
    }
    @Test
    public void saveByLatLonTestBadRequest() throws IOException {
        assertTrue(HttpStatus.BAD_REQUEST.equals(recordService.saveByLatLong(100.0,100.0).getStatusCode()));
    }
    @Test
    public void saveByLatLonTestBadRequestNone() throws IOException {
        assertTrue(HttpStatus.BAD_REQUEST.equals(recordService.saveByLatLong(null,null).getStatusCode()));
    }

}
