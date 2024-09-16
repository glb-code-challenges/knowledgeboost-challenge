package com.globant.weather.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "weather_data")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(name="response_code")
    private int responseCode;
    @NonNull
    @Column(name="message")
    private String message;
    @NonNull
    @Column(name="city_name")
    private String cityName;
    @NonNull
    @Column(name="request_date")
    private LocalDateTime requestDate;
}
