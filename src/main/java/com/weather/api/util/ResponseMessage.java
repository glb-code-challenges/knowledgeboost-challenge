package com.weather.api.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResponseMessage {
    SUCCESS("Successful"),
    FAILURE("Unsuccesful");

    private final String description;
}
