// Homework.java
package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Homework {

    public static void main(String[] args) {
        try (Connection connection = Connector.getConnection()) {
            BookTableCreator.createBookTable(connection); // Создаем таблицу 'book'
            BookImporter.importBooksFromFile("books.txt", connection); // Считываем книги из файла и добавляем в базу данных
            BookSorterPrinter.sortAndPrintBooksByAuthor("author.txt",connection); // Сортируем и выводим результат

        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
