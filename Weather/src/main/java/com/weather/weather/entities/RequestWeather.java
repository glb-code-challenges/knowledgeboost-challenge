package com.weather.weather.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class RequestWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String cityName;
    private String latitude;
    private String longitude;
    private UUID idOperacion;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private TypeRequest typeRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRequest typeRequest) {
        this.typeRequest = typeRequest;
    }

    @PrePersist
    private void prePersist(){
        this.date = LocalDateTime.now();
        this.idOperacion = UUID.randomUUID();
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public UUID getIdOperacion(){return this.idOperacion;}

    public enum TypeRequest{
        CITYNAME,
        LATLONG
    }
}