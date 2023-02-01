package com.weatherapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

	private Integer code;
	
	private String message;
	
	private Object body;
	
	private String details;
	
}
