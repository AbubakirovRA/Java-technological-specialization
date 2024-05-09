package ru.surgu.medexambackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "examiner")
@NoArgsConstructor
@Getter
@Setter
public class Examiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", unique = true) // Ограничение уникальности для full_name
    private String fullName;

    @Column(name = "medical_specialty")
    private String medicalSpecialty;

    @OneToMany(mappedBy = "examinerId")
    private List<Protocol> protocols;

    public Examiner(String fullName, String medicalSpecialty) {
        this.fullName = fullName;
        this.medicalSpecialty = medicalSpecialty;
    }

    // Пустой конструктор и конструктор со всеми аргументами,
    // а также геттеры и сеттеры создаются при помощи lombok
}
