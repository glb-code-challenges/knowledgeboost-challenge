package com.globant.dnlpalestina.WeatherService.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="weather")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "coords")
    private String coords;

    @Column(name = "root_cause")
    private String rootCause;

}
