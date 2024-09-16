package com.globant.talentpool.weather.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="records")
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="record_id")
    private Long recordId;
    @Column(name="record_city_name")
    private String recordCity;
    @Column(name="record_request_timestamp")
    private Date recordTimestamp;
    @Column(name="record_status_code")
    private int recordStatusCode;
    @Column(name="record_body_response")
    private String recordBodyResponse;


}
