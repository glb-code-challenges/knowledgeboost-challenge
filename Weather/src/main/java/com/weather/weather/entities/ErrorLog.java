package com.weather.weather.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ErrorLog{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private UUID idOperacion;
    private Integer statusCode;
    private String error;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(UUID idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    @PrePersist
    public void prePersist(){
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ErrorLog{" +
                "id=" + id +
                ", idOperacion=" + idOperacion +
                ", statusCode=" + statusCode +
                ", error='" + error + '\'' +
                ", date=" + date +
                '}';
    }
}