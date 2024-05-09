package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.*;
import ru.surgu.medexambackend.service.ProtocolService;

import java.util.List;

@RestController
@RequestMapping("/protocols")
public class ProtocolController {

    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    // Эндпоинт для сохранения протокола
    @Operation(summary = "Save protocol")
    @PostMapping
    public ResponseEntity<Protocol> saveProtocol(@RequestBody Protocol protocol) {
        Protocol savedProtocol = protocolService.saveProtocol(protocol);
        return new ResponseEntity<>(savedProtocol, HttpStatus.CREATED);
    }

    // Эндпоинт для поиска протокола по его ID
    @Operation(summary = "Find protocol by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Protocol> findProtocolById(@PathVariable int id) {
        Protocol protocol = protocolService.findProtocolById(id);
        return protocol != null ? ResponseEntity.ok(protocol) : ResponseEntity.notFound().build();
    }

    // Эндпоинт для поиска паспорта станции по ID протокола
    @Operation(summary = "Find station passport by protocol ID")
    @GetMapping("/{protocolId}/station-passport")
    public ResponseEntity<StationPassport> findStationPassportByProtocolId(@PathVariable int protocolId) {
        StationPassport stationPassport = protocolService.findStationPassportByProtocolId(protocolId);
        return stationPassport != null ? ResponseEntity.ok(stationPassport) : ResponseEntity.notFound().build();
    }

    // Эндпоинт для поиска сценариев по ID протокола и ID паспорта станции
    @Operation(summary = "Find scenarios by protocol ID and station passport ID")
    @GetMapping("/{protocolId}/scenarios/{stationPassportId}")
    public ResponseEntity<List<SituationsList>> findScenariosByProtocolIdAndStationPassportId(@PathVariable int protocolId, @PathVariable int stationPassportId) {
        List<SituationsList> scenarios = protocolService.findScenariosByProtocolIdAndStationPassportId(protocolId, stationPassportId);
        return ResponseEntity.ok(scenarios);
    }

    // Эндпоинт для поиска действий по ID протокола, ID паспорта станции и ID сценария
    @Operation(summary = "Find actions by protocol ID, station passport ID, and scenario ID")
    @GetMapping("/{protocolId}/actions/{stationPassportId}/{scenarioId}")
    public ResponseEntity<List<ActionsList>> findActionsByProtocolAndStationPassportAndScenario(@PathVariable int protocolId, @PathVariable int stationPassportId, @PathVariable int scenarioId) {
        List<ActionsList> actions = protocolService.findActionsByProtocolAndStationPassportAndScenario(protocolId, stationPassportId, scenarioId);
        return ResponseEntity.ok(actions);
    }

    // Эндпоинт для поиска результатов выполнения действий по ID протокола и списку действий
    @Operation(summary = "Find results by actions and protocol ID")
    @PostMapping("/{protocolId}/results")
    public ResponseEntity<List<ExamInfo>> findResultsByActionsAndProtocol(@PathVariable int protocolId, @RequestBody List<ActionsList> actionsList) {
        List<ExamInfo> results = protocolService.findResultsByActionsAndProtocol(actionsList, protocolId);
        return ResponseEntity.ok(results);
    }
}


//Создание протокола:
//Метод: POST
//Путь: /protocols
//Описание: Создает новый протокол.

//Получение протокола по его ID:
//Метод: GET
//Путь: /protocols/{id}
//Описание: Возвращает протокол с указанным ID, если такой существует.

//Получение паспорта станции по ID протокола:
//Метод: GET
//Путь: /protocols/{protocolId}/station-passport
//Описание: Возвращает паспорт станции, связанный с протоколом по его ID.

//Получение сценариев по ID протокола и ID паспорта станции:
//Метод: GET
//Путь: /protocols/{protocolId}/scenarios/{stationPassportId}
//Описание: Возвращает список сценариев, связанных с протоколом и паспортом станции.

//Получение действий по ID протокола, ID паспорта станции и ID сценария:
//Метод: GET
//Путь: /protocols/{protocolId}/actions/{stationPassportId}/{scenarioId}
//Описание: Возвращает список действий, связанных с протоколом, паспортом станции и сценарием.

//Получение результатов выполнения действий по ID протокола и списку действий:
//Метод: POST
//Путь: /protocols/{protocolId}/results
//Описание: Возвращает результаты выполнения действий для указанного протокола и списка действий.
