package com.marco.estrada.weather.WeatherPersistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "request_data")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_date")
    Date operationDate;

    @Column(name = "city_name")
    String cityName;

    @Column(name = "response_code")
    Integer responseCode;

    @OneToOne(mappedBy = "requestData")
    Error error;
}
