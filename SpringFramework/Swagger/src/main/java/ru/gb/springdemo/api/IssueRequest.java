package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель запроса на выдачу книги")
public class IssueRequest {

  @Schema(description = "Идентификатор книги", required = true)
  private long bookId;

  @Schema(description = "Идентификатор читателя", required = true)
  private long readerId;

  // Другие поля и методы класса

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }
}
