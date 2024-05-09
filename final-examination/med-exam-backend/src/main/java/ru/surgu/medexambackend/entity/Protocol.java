package ru.surgu.medexambackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import lombok.Data;

@Entity(name = "protocol")
@Data
public class Protocol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "exam_date")
    @PastOrPresent //условие, чтобы дата экзамена не могла быть позже текущей даты
    private LocalDate examDate;

    @ManyToOne
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examinerId;

    @ManyToOne
    @JoinColumn(name = "examinable_id", referencedColumnName = "id")
    private Examinable examinableId;

    @ManyToOne
    @JoinColumn(name = "accreditation_type_id", referencedColumnName = "id")
    private AccreditationType accreditationTypeId;

    @ManyToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specializationId;

    @ManyToOne
    @JoinColumn(name = "station_passport_id", referencedColumnName = "id")
    private StationPassport stationPassportId;

    @ManyToOne
    @JoinColumn(name = "scenario_number_id", referencedColumnName = "id")
    private SituationsList scenarioNumberId;

    @Column(name = "total_points")
    private int totalPoints;

    // Конструкторы, геттеры и сеттеры создаются при помощи lombok
}
