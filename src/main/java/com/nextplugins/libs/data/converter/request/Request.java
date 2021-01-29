package com.nextplugins.libs.data.converter.request;

import com.nextplugins.libs.data.converter.response.Response;
import com.nextplugins.libs.data.converter.result.SimpleResultSet;
import lombok.Data;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.function.Function;

@Data
public class Request<V> {

    private final SimpleResultSet simpleResultSet;

    public Response<V> convert(Function<SimpleResultSet, V> function) {
        try {
            final HashSet<V> set = new HashSet<>();
            final ResultSet resultSet = simpleResultSet.getResultSet();

            while (resultSet.next()) {
                set.add(function.apply(simpleResultSet));
            }

            simpleResultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response<>(new HashSet<>());
    }
}
