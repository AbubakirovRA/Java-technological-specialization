package org.example;

import java.io.IOException;
import java.sql.SQLException;


public class Homework {

    public static void main(String[] args) throws SQLException, IOException {
        // Создаем таблицу 'book' с использованием Hibernate/JPA
        JpaBookTableCreator.createBookTable();

        // Создаем и сохраняем 10 книг с использованием Hibernate/JPA
        JpaBookCreator.createAndSaveBooks("src/main/resources/books.txt");

        // Загружаем и выводим список книг какого-то автора с использованием Hibernate/JPA
        JpaBookLoader.loadBooksByAuthorFromFile("src/main/resources/author.txt");
    }
}
