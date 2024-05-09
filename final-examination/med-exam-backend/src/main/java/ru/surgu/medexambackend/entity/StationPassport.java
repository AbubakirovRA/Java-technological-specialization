package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Entity(name = "station_passport")
@Data
public class StationPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "passport_name")
    private String passportName; // название паспорта станции

    @OneToMany(mappedBy = "stationPassportId")
    private Set<Protocol> protocols;

    @OneToMany(mappedBy = "stationPassportId")
    private Set<SituationsList> situationsLists;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
