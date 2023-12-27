// BookTableCreator.java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BookTableCreator {
    public static void createBookTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Create the 'book' table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS book (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "author VARCHAR(255)" +
                    // Add other columns as needed
                    ");";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'book' created successfully.");
        }
    }
}
