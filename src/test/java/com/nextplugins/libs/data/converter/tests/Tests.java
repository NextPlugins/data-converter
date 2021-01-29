package com.nextplugins.libs.data.converter.tests;

import com.nextplugins.libs.data.converter.Converter;
import com.nextplugins.libs.data.converter.tests.model.Administrator;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class Tests {

    protected Connection connection;

    @Test
    @BeforeAll
    protected void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");

            this.connection = DriverManager.getConnection("jdbc:sqlite:memory.sqlite");

            assertTrue(connection.isValid(10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Inserting default data on database")
    @BeforeAll
    protected void insertDefaultData() {
        final String createTable = "CREATE TABLE `users` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL);";

        try (PreparedStatement statement = connection.prepareStatement(createTable)) {
            assertNotNull(statement);

            statement.executeUpdate();

            final String insertData = "INSERT INTO `users` VALUES ('1', 'Eike');";

            statement.executeUpdate(insertData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Converting the data")
    protected void convertingData() {
        Converter.<Administrator>builder()
                .connection(connection)
                .create()
                .request("SELECT `id`, `name` FROM `users`")
                .convert((resultSet) -> new Administrator(resultSet.get("id"), resultSet.get("name"), "MASTER"))
                .responseEach((admin) -> {
                    assertEquals(1, admin.getId());
                    assertEquals("Eike", admin.getName());
                    assertEquals("MASTER", admin.getRole());
                });
    }

    @Test
    @AfterAll
    protected void closeConnection() {
        try {
            try (PreparedStatement statement = connection.prepareStatement("DROP TABLE `users`")) {
                statement.executeUpdate();
            }

            if (!connection.isClosed()) connection.close();

            assertTrue(connection.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
