package com.globant.openweatherservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SysDto {
    private Integer type;
    private Long id;
    private String country;
    private Long sunrise;
    private Long sunset;
}
