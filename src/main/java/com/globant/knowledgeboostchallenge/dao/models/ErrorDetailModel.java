package com.globant.knowledgeboostchallenge.dao.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetailModel {
    private Date date;
    private String code;
    private String message;
    private String details;
    private String exception;

    public ErrorDetailModel(Date date, String message, String details, String code, String exception) {
        this.date = date;
        this.message = message;
        this.details = details;
        this.code = code;
        this.exception = exception;
    }
}
