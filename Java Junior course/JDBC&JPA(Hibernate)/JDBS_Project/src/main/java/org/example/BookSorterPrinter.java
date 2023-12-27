// BookSorterPrinter.java
package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSorterPrinter {

    public static void sortAndPrintBooksByAuthor(String authorFilePath, Connection connection) throws SQLException, IOException {
        // Read the author from the file
        String author = Files.lines(Paths.get(authorFilePath)).findFirst().orElse("").trim();

        // Select books from 'book' table where author matches
        String selectQuery = "SELECT * FROM book WHERE author = ? ORDER BY author";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, author);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the ResultSet
                System.out.println("Books by author '" + author + "' sorted by author:");
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String authorName = resultSet.getString("author");
                    System.out.println("ID: " + id + ", Name: " + name + ", Author: " + authorName);
                }
            }
        }
    }
}