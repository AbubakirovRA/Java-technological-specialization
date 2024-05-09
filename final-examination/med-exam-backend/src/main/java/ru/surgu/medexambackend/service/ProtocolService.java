package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.*;
import ru.surgu.medexambackend.repository.ProtocolRepository;

import java.util.List;

@Service
public class ProtocolService {

    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolService(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    // Стандартный метод для сохранения протокола
    public Protocol saveProtocol(Protocol protocol) {
        return protocolRepository.save(protocol);
    }

    // Стандартный метод для поиска протокола по его ID
    public Protocol findProtocolById(int id) {
        return protocolRepository.findById(id).orElse(null);
    }

    // Метод для поиска паспорта станции по ID протокола
    public StationPassport findStationPassportByProtocolId(int protocolId) {
        return protocolRepository.findStationPassportByProtocolId(protocolId);
    }

    // Метод для поиска сценариев по ID протокола и ID паспорта станции
    public List<SituationsList> findScenariosByProtocolIdAndStationPassportId(int protocolId, int stationPassportId) {
        return protocolRepository.findScenariosByProtocolIdAndStationPassportId(protocolId, stationPassportId);
    }

    // Метод для поиска действий по ID протокола, ID паспорта станции и ID сценария
    public List<ActionsList> findActionsByProtocolAndStationPassportAndScenario(int protocolId, int stationPassportId, int scenarioId) {
        return protocolRepository.findActionsByProtocolAndStationPassportAndScenario(protocolId, stationPassportId, scenarioId);
    }

    // Метод для поиска результатов выполнения действий по ID протокола и списку действий
    public List<ExamInfo> findResultsByActionsAndProtocol(List<ActionsList> actionsList, int protocolId) {
        return protocolRepository.findResultsByActionsAndProtocol(actionsList, protocolId);
    }
}

