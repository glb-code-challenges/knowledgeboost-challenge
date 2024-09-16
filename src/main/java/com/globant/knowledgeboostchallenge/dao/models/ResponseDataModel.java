package com.globant.knowledgeboostchallenge.dao.models;

import lombok.*;

@Getter
@Setter
public class ResponseDataModel<T>{
    private boolean succeeded;
    private T data;
    private ErrorDetailModel errorDetail;
}