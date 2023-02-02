package com.glb.knowledgeboostchallenge.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RESPONSE_LOG")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "DATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime callDateTime;

    @Column(name = "RESPONSE_CODE")
    private int responseCode;

    @Column(name = "ERROR_CAUSE")
    private String errorCause;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "RESPONSE_DATA", columnDefinition="TEXT")
    private String responseJson;

}
