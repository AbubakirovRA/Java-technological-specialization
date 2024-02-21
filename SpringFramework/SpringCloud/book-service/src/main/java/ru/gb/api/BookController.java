package ru.gb.api;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BookController {
  private final List<Book> books;
  private final Random random = new Random();
  private final Faker faker = new Faker();

  public BookController() {
    this.books = generateBooks();
  }

  @GetMapping
  public List<Book> getAll() {
    return books;
  }

  @GetMapping("/random")
  public Book getRandom() {
    int randomIndex = random.nextInt(books.size());
    return books.get(randomIndex);
  }

  private List<Book> generateBooks() {
    List<Book> generatedBooks = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      Book book = new Book();
      book.setId(UUID.randomUUID());
      book.setName(faker.book().title());

      Author author = new Author();
      author.setId(UUID.randomUUID());
      author.setFirstName(faker.name().firstName());
      author.setLastName(faker.name().lastName());
      book.setAuthor(author);

      generatedBooks.add(book);
    }
    return generatedBooks;
  }
}
