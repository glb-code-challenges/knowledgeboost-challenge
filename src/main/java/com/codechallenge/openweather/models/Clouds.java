package com.codechallenge.openweather.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class Clouds implements Serializable {

	private Long all;
	private static final long serialVersionUID = -2698648682866615452L;

}
