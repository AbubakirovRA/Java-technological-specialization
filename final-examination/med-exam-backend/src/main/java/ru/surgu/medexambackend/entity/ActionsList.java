package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity(name = "actions_list")
@Data
public class ActionsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "group_characteristic")
    private String groupCharacteristic;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
