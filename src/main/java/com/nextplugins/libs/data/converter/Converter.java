package com.nextplugins.libs.data.converter;

import com.nextplugins.libs.data.converter.request.Request;
import com.nextplugins.libs.data.converter.result.SimpleResultSet;
import lombok.Builder;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;

public final class Converter<V> {

    @Getter private final Connection connection;

    @Builder(buildMethodName = "create")
    public Converter(Connection connection) {
        this.connection = connection;
    }

    public Request<V> request(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            return new Request<>(SimpleResultSet.of(statement.executeQuery()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Request<>(null);
    }

}
