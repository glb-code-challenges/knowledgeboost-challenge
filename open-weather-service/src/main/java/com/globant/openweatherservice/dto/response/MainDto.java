package com.globant.openweatherservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainDto {
    private Float temp;
    private Float feels_like;
    private Float temp_min;
    private Float temp_max;
    private Float pressure;
    private Float humidity;

}
