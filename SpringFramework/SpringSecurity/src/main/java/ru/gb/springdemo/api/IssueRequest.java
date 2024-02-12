package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Модель запроса на выдачу книги")
public class IssueRequest {

  @Schema(description = "Идентификатор книги", required = true)
  private long bookId;

  @Schema(description = "Идентификатор читателя", required = true)
  private long readerId;

  // Другие поля и методы класса

}
