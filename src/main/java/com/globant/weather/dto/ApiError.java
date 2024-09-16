package com.globant.weather.dto;

import lombok.Data;

@Data
public class ApiError {
    String cod;
    String message;
}
