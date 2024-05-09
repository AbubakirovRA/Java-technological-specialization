package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.*;

import java.util.List;

@Repository
public interface ExaminableRepository extends JpaRepository<Examinable, Integer> {

//    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository

    // Получение информации о том, в каких экзаменах участвовал конкретный экзаменуемый
    @Query("SELECT e FROM exam e JOIN e.protocolId p JOIN p.examinableId ex WHERE ex.id = :examinableId")
    List<Exam> findExamsByExaminableId(@Param("examinableId") int examinableId);


    // Получение информации о том, какие протоколы содержатся в экзаменах, которые сдал экзаменуемый
    @Query("SELECT DISTINCT pr FROM protocol pr JOIN examinable ex ON ex.id = pr.examinableId.id")
    List<Protocol> findProtocolsByExaminableId(@Param("examinableId") int examinableId);


    // Получение информации о паспортах станций, в которых участвовал экзаменуемый
    @Query("SELECT sp FROM station_passport sp JOIN sp.protocols p JOIN p.examinableId ex WHERE ex.id = :examinableId")
    List<StationPassport> findStationPassportsByExaminableId(@Param("examinableId") int examinableId);


    // Получение информации о сценариях, в которых участвовал экзаменуемый
    @Query("SELECT DISTINCT sp.passportName, sl.scenarioNumber " +
            "FROM station_passport sp " +
            "JOIN sp.protocols p " +
            "JOIN p.examinableId ex " +
            "JOIN situations_list sl ON sl.stationPassportId.id = sp.id " +
            "WHERE ex.id = :examinableId")
    List<Object[]> findScenarioNumbersByExaminableId(@Param("examinableId") int examinableId);


    // Получение информации о действиях, в которых участвовал экзаменуемый
    @Query("SELECT al.actionName " +
            "FROM protocol p " +
            "JOIN exam_info ei ON p.id = ei.protocolId.id " +
            "JOIN actions_list al ON ei.actionId.id = al.id " +
            "WHERE p.examinableId.id = :examinableId")
    List<String> findActionsByExaminableId(@Param("examinableId") int examinableId);


//    // Получение списка экзаменуемых для конкретного экзамена
@Query("SELECT ex.fullName " +
        "FROM exam e " +
        "JOIN protocol p ON e.protocolId.id = p.id " +
        "JOIN examinable ex ON p.examinableId.id = ex.id " +
        "WHERE e.id = :examId")
List<String> findExamineesByExamId(@Param("examId") int examId);
}

