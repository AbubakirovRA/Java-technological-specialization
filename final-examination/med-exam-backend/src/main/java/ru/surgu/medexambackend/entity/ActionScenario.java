package ru.surgu.medexambackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "action_scenario")
@Data
public class ActionScenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Связь с действием
    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private ActionsList actionId;

    // Связь с номером сценария
    @ManyToOne
    @JoinColumn(name = "scenario_number_id", referencedColumnName = "id")
    private SituationsList scenarioNumberId;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
