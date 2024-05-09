package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.*;

import java.util.List;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Integer> {

    //    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository

    // Получение информации о том, какой паспорт станции у заданного протокола
    @Query("SELECT p.stationPassportId FROM protocol p WHERE p.id = :protocolId")
    StationPassport findStationPassportByProtocolId(@Param("protocolId") int protocolId);

    // Получение информации о том, какой сценарий у протокола для данного паспорта станции
    @Query("SELECT s FROM situations_list s " +
            "JOIN protocol p ON p.scenarioNumberId = s " +
            "WHERE p.id = :protocolId " +
            "AND p.stationPassportId.id = :stationPassportId")
    List<SituationsList> findScenariosByProtocolIdAndStationPassportId(@Param("protocolId") int protocolId, @Param("stationPassportId") int stationPassportId);


    // Получение списка действий для заданного id сценария, id паспорта станции, id протокола
    @Query("SELECT al FROM actions_list al " +
            "JOIN action_scenario as ON as.actionId = al " +
            "JOIN situations_list sl ON sl = as.scenarioNumberId " +
            "JOIN protocol p ON p.scenarioNumberId = sl " +
            "WHERE p.id = :protocolId " +
            "AND p.stationPassportId.id = :stationPassportId " +
            "AND sl.id = :scenarioId")
    List<ActionsList> findActionsByProtocolAndStationPassportAndScenario(@Param("protocolId") int protocolId, @Param("stationPassportId") int stationPassportId, @Param("scenarioId") int scenarioId);


    // Получение результата выполнения по каждому действию для заданного сценария, заданного паспорта станции, заданного протокола
    @Query("SELECT ei FROM exam_info ei " +
            "WHERE ei.actionId IN :actionsList " +
            "AND ei.protocolId.id = :protocolId")
    List<ExamInfo> findResultsByActionsAndProtocol(@Param("actionsList") List<ActionsList> actionsList, @Param("protocolId") int protocolId);
}
