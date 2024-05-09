package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Data;

@Entity(name = "exam_info")
@Data
public class ExamInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "protocol_id", referencedColumnName = "id")
    private Protocol protocolId;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private ActionsList actionId;

    @Column(columnDefinition = "BOOLEAN") // Это позволит базе данных ограничивать значения атрибута result только логическими (true или false)
    private boolean result;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
