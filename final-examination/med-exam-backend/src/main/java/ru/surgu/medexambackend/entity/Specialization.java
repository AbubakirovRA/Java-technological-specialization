package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Entity(name = "specialization")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "specialization_name ")
    private String specializationName; // название специализации

    // Пустой конструктор и конструктор со всеми аргументами,
    // а также геттеры и сеттеры создаются при помощи lombok
}
