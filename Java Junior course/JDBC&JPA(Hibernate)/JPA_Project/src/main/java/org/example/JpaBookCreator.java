package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JpaBookCreator {

    public static void createAndSaveBooks(String dataFilePath) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.example");
            entityManager = entityManagerFactory.createEntityManager();

            // Начать транзакцию
            entityManager.getTransaction().begin();

            // Создать и сохранить книги
            int numberOfBooks = getNumberOfBooks(dataFilePath);
            for (int i = 1; i <= numberOfBooks; i++) {
                String bookData = getBookData(dataFilePath, i);
                String[] parts = bookData.split(",");
                if (parts.length == 2) {
                    String bookName = parts[0].trim();
                    String authorName = parts[1].trim();

                    // Поиск или создание автора
                    Author author = findOrCreateAuthor(entityManager, authorName);

                    // Вывести информацию о книге в консоль перед сохранением
                    System.out.println("Book data - Name: " + bookName + ", Author: " + authorName);

                    // Вывести информацию об авторе в консоль перед сохранением
                    System.out.println("Author data - Name: " + authorName);

                    // Создать и сохранить книгу в базе данных
                    Book book = new Book();
                    book.setName(bookName);
                    book.setAuthor(author);
                    entityManager.persist(book);

                    // Вывести информацию о книге после сохранения
                    System.out.println("Added book - Name: " + book + ", Author: " + author);

                    // Вывести информацию о книге в консоль
                    System.out.println("Added book - Name: " + bookName + ", Author: " + authorName + ", ID: " + book.getId() + ", Author ID: " + author.getId());
                }
            }

            // Завершить транзакцию
            entityManager.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                // Откатить транзакцию в случае ошибки
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }

    private static int getNumberOfBooks(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            return lines;
        }
    }

    private static String getBookData(String filePath, int index) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 1; i <= index; i++) {
                String line = reader.readLine();
                if (line == null) {
                    // Вернуть первую строку, если достигнут конец файла
                    reader.reset();
                    line = reader.readLine();
                }
            }
            return reader.readLine();
        }
    }

    private static Author findOrCreateAuthor(EntityManager entityManager, String authorName) {
        List<Author> authors = entityManager.createQuery("SELECT a FROM Author a WHERE a.name = :authorName", Author.class)
                .setParameter("authorName", authorName)
                .getResultList();

        if (!authors.isEmpty()) {
            // Если автор уже существует, вернуть его
            return authors.get(0);
        } else {
            // Если автора нет, создать нового и вернуть его
            Author newAuthor = new Author();
            newAuthor.setName(authorName);
            entityManager.persist(newAuthor);
            return newAuthor;
        }
    }
}
