// Reader.java
package ru.gb.springdemo.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


// Класс Reader
@Setter
@Getter
@Entity
@Table(name = "readers") // Указываем имя таблицы в базе данных
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Указываем, что поле name не может быть null
    private String name;

  // Геттеры, сеттеры обеспечивает lombok


}
