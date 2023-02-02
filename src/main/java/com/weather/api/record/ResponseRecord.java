package com.weather.api.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.weather.api.util.ResponseMessage;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseRecord<T>(ResponseMessage responseMessage,
                                String message,
                                Throwable causeError,
                                T content) {
}
