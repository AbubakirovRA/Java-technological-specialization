package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.SituationsList;
import ru.surgu.medexambackend.repository.SituationsListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SituationsListService {

    private final SituationsListRepository situationsListRepository;

    @Autowired
    public SituationsListService(SituationsListRepository situationsListRepository) {
        this.situationsListRepository = situationsListRepository;
    }

    // Получение всех сценариев
    public List<SituationsList> findAllSituationsLists() {
        return situationsListRepository.findAll();
    }

    // Получение сценария по его ID
    public Optional<SituationsList> findSituationsListById(int id) {
        return situationsListRepository.findById(id);
    }

    // Сохранение сценария
    public SituationsList saveSituationsList(SituationsList situationsList) {
        return situationsListRepository.save(situationsList);
    }

    // Удаление сценария по его ID
    public void deleteSituationsListById(int id) {
        situationsListRepository.deleteById(id);
    }

    // Получение списка сценариев по заданным id протокола и id паспорта станции
    public List<SituationsList> findScenariosByProtocolIdAndStationPassportId(int protocolId, int stationPassportId) {
        return situationsListRepository.findScenariosByProtocolIdAndStationPassportId(protocolId, stationPassportId);
    }
}

