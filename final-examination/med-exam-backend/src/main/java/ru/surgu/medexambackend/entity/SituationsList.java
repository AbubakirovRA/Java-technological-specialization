package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity(name = "situations_list")
@Data
public class SituationsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "scenario_number")
    private int scenarioNumber;

    @ManyToOne
    @JoinColumn(name = "station_passport_id")
    private StationPassport stationPassportId;

    private String description;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
