package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;

import java.util.List;

// Интерфейс BookRepository
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookById(Long id);

    Book createBook(Book book);

    void deleteBookById(Long id);

    List<Book> getAllBooks();
}
