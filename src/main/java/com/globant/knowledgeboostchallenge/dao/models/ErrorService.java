package com.globant.knowledgeboostchallenge.dao.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ErrorService implements Serializable {
	private static final long serialVersionUID = 2123978568947621505L;

	@JsonProperty("cod")
	public String cod;

	@JsonProperty("message")
	public String message;
}
