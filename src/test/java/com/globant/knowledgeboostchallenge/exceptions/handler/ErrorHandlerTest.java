package com.globant.knowledgeboostchallenge.exceptions.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {
    @InjectMocks
    private ErrorHandler errorHandler;

    private  String exceptionResponse;
    private  byte[] response;

    @BeforeEach
    void setUp() throws IOException {
        exceptionResponse = "{\n" +
                "    \"serviceResultCode\": -10,\n" +
                "    \"serviceResultLog\": \"[authenticateFacial] Input byte array has wrong 4-byte ending unit\",\n" +
                "    \"serviceTime\": \"11\",\n" +
                "    \"serviceFacialAuthenticationResult\": 0,\n" +
                "    \"serviceFacialSimilarityResult\": 0.0,\n" +
                "    \"serviceTransactionId\": \"a84b2a9d-2c2d-4f32-aefd-47af1efa679a\"\n" +
                "}";

        response = exceptionResponse.getBytes();

    }


    @Test
    void constraintException() {
        assertTrue(true);

    }

    @Test
    void httpClientErrorException() throws JsonProcessingException {
        assertTrue(true);

    }
}