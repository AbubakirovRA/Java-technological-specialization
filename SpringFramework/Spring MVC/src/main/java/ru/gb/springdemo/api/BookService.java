package ru.gb.springdemo.api;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(long id) {
        return BookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    public void deleteBookById(long id) {
        bookRepository.deleteBookById(id);
    }
}

