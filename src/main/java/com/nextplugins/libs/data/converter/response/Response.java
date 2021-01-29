package com.nextplugins.libs.data.converter.response;

import java.util.HashSet;
import java.util.function.Consumer;

public class Response<V> {

    private final HashSet<V> set;

    public Response(HashSet<V> set) {
        this.set = set;
    }

    public void response(Consumer<HashSet<V>> consumer) {
        consumer.accept(set);
    }

    public void responseEach(Consumer<V> consumer) {
        for (V value : set) {
            consumer.accept(value);
        }
    }

}
