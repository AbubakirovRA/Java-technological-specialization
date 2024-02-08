// Reader.java
package ru.gb.springdemo.model;

import javax.persistence.*;


// Класс Reader
@Entity
@Table(name = "readers") // Указываем имя таблицы в базе данных
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Указываем, что поле name не может быть null
    private String name;

  // Геттеры, сеттеры и другие поля...

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
