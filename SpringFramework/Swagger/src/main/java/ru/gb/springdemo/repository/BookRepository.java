package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

// Интерфейс BookRepository
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Все необходимые методы доступа к данным предоставляет JpaRepository, дополнительные методы не требуются
}
