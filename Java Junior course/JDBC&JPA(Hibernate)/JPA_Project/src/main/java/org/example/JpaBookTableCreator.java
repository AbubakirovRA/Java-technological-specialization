package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaBookTableCreator {

    public static void createBookTable() {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.example");
            entityManager = entityManagerFactory.createEntityManager();

            // Начать транзакцию
            entityManager.getTransaction().begin();

            // Создать таблицу
            entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS book (id bigint not null auto_increment, author varchar(255), name varchar(255), primary key (id)) ENGINE=MyISAM").executeUpdate();

            // Завершить транзакцию
            entityManager.getTransaction().commit();
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

