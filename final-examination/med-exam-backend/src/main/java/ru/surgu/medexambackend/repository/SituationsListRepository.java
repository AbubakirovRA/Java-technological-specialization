package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.SituationsList;

import java.util.List;

@Repository
public interface SituationsListRepository extends JpaRepository<SituationsList, Integer> {

    //    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository

    // Получение списка номеров сценариев по заданным id протокола и id паспорта станции
    @Query("SELECT s FROM situations_list s " +
            "JOIN protocol p ON p.scenarioNumberId = s " +
            "WHERE p.id = :protocolId " +
            "AND p.stationPassportId.id = :stationPassportId")
    List<SituationsList> findScenariosByProtocolIdAndStationPassportId(@Param("protocolId") int protocolId, @Param("stationPassportId") int stationPassportId);
}

