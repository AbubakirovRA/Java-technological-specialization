// BookImporter.java
package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookImporter {
    public static void importBooksFromFile(String filePath, Connection connection) throws IOException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (name, author) VALUES (?, ?)")) {
            Files.lines(Paths.get(filePath)).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        preparedStatement.setString(1, parts[0].trim());
                        preparedStatement.setString(2, parts[1].trim());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.err.println("Error inserting book: " + e.getMessage());
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            });
            System.out.println("Books imported from file.");
        }
    }
}
