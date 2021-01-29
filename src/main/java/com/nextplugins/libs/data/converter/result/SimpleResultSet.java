package com.nextplugins.libs.data.converter.result;

import lombok.Data;

import java.sql.ResultSet;

@Data(staticConstructor = "of")
public class SimpleResultSet implements AutoCloseable {

    private final ResultSet resultSet;

    public <T> T get(String column) {
        try {
            return (T) resultSet.getObject(column);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {
        try {
            if (resultSet != null)
            if (!resultSet.isClosed()) resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
