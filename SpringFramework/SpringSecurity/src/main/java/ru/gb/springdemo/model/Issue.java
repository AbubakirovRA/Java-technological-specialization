package ru.gb.springdemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// Класс Issue
@Setter
@Getter
@Entity
@Table(name = "issues") // Указываем имя таблицы в базе данных
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private long book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader name;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public void setBookId(long book) {
        this.book = book;
    }

    public void setReaderId(long readerId) {
    }
}
