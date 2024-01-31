package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookRepository {

    private static List<Book> books;

    public BookRepository() {
        BookRepository.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("война и мир"),
                new Book("метрвые души"),
                new Book("чистый код")
        ));
    }

    public static Book getBookById(long id) {
        Optional<Book> optionalBook = books.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();

        return optionalBook.orElse(null);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void save(Book book) {
        books.add(book);
    }

    public void deleteBookById(long id) {
        books.removeIf(book -> Objects.equals(book.getId(), id));
    }
}
