package ru.surgu.medexambackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "examinable")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Examinable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", unique = true) // Ограничение уникальности для full_name
    private String fullName;

    private String specialization;

    @Column(name = "accreditation_type_id") //
    private int accreditationTypeId;

    public Examinable(String fullName, String specialization, int accreditationTypeId) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.accreditationTypeId = accreditationTypeId;
    }

    // Пустой конструктор и конструктор со всеми аргументами,
    // а также геттеры и сеттеры создаются при помощи lombok
}