package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity(name = "exam")
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "protocol_id", referencedColumnName = "id")
    private Protocol protocolId;

    @ManyToOne
    @JoinColumn(name = "examinable_id", referencedColumnName = "id")
    private Examinable examinableId;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
