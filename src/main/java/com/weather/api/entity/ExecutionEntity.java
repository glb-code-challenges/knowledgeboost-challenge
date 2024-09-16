package com.weather.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Executions")
@Getter
@Setter
public class ExecutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "root_cause")
    private String rootCause;

    @Column(name = "city_name")
    private String cityName;
}
