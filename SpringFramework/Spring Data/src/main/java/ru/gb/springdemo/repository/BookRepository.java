package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdemo.model.Book; // Убедитесь, что это правильный пакет для вашего класса Book

public interface BookRepository extends JpaRepository<Book, Long> {
    // Дополнительные методы (если нужны)
}