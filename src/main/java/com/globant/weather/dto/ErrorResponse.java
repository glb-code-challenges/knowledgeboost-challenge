package com.globant.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private List<String> errors;
    private LocalDateTime timeStamp;
}
