package ru.surgu.medexambackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "authentication")
@Data
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examinerId;

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
