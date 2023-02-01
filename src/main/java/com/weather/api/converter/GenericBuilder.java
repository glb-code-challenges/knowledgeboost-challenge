package com.weather.api.converter;

import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public class GenericBuilder<T> {

    private final T instance;

    public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<>(instantiator.get());
    }

    public <U> GenericBuilder<T> with(BiConsumer<T, U> consumer, U value) {
        consumer.accept(instance, value);
        return this;
    }

    public <U> GenericBuilder<T> map(BiConsumer<T, U> consumer, Supplier<U> supplier) {
        return with(consumer, supplier.get());
    }

    public T build() {
        return instance;
    }

    public <U> U buildAndThen(Function<T, U> function) {
        return function.apply(instance);
    }

    public void buildAndConsume(Consumer<T> consumer) {
        consumer.accept(instance);
    }
}
