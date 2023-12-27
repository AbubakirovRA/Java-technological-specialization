package org.example;

import org.hibernate.internal.build.AllowSysOut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JpaBookLoader {

    public static void loadBooksByAuthorFromFile(String filePath) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.example");
            entityManager = entityManagerFactory.createEntityManager();

            // Начать транзакцию
            entityManager.getTransaction().begin();

            // Считать автора из файла
            String authorName;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                authorName = reader.readLine();
            }

            System.out.println("Содержимое файла author.txt --" + authorName);

            // Запрос на выборку книг по автору из файла
            String query = "SELECT b FROM Book b WHERE b.author.name = :author";
            List<Book> books = entityManager.createQuery(query, Book.class)
                    .setParameter("author", authorName)
                    .getResultList();

            // Вывести результаты в консоль
            System.out.println("Books by author '" + authorName + "':");
            for (Book book : books) {
                System.out.println("ID: " + book.getId() + ", Name: " + book.getName() + ", Author: " + book.getAuthor().getName());
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
}

