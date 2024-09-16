package com.weather.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;

public interface GenericConverter<T, U> extends Converter<T, U> {

    default List<U> convert(List<T> tList) {
        return tList == null ? null : tList.stream().map(this::convert).toList();
    }
}
