package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.SituationsList;
import ru.surgu.medexambackend.service.SituationsListService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/situations-lists")
public class SituationsListController {

    private final SituationsListService situationsListService;

    @Autowired
    public SituationsListController(SituationsListService situationsListService) {
        this.situationsListService = situationsListService;
    }

    // Получение всех сценариев
    @Operation(summary = "Get all situations lists")
    @GetMapping
    public ResponseEntity<List<SituationsList>> getAllSituationsLists() {
        List<SituationsList> situationsLists = situationsListService.findAllSituationsLists();
        return ResponseEntity.ok(situationsLists);
    }

    // Получение сценария по его ID
    @Operation(summary = "Get a situations list by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the situations list"),
            @ApiResponse(responseCode = "404", description = "Situations list not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SituationsList> getSituationsListById(@PathVariable("id") int id) {
        Optional<SituationsList> situationsList = situationsListService.findSituationsListById(id);
        return situationsList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Создание нового сценария
    @Operation(summary = "Create a new situations list")
    @PostMapping
    public ResponseEntity<SituationsList> createSituationsList(@RequestBody SituationsList situationsList) {
        SituationsList createdSituationsList = situationsListService.saveSituationsList(situationsList);
        return new ResponseEntity<>(createdSituationsList, HttpStatus.CREATED);
    }

    // Обновление информации о сценарии по его ID
    @Operation(summary = "Update a situations list by ID")
    @PutMapping("/{id}")
    public ResponseEntity<SituationsList> updateSituationsList(@PathVariable("id") int id, @RequestBody SituationsList updatedSituationsList) {
        if (!situationsListService.findSituationsListById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedSituationsList.setId(id);
        SituationsList savedSituationsList = situationsListService.saveSituationsList(updatedSituationsList);
        return ResponseEntity.ok(savedSituationsList);
    }

    // Удаление сценария по его ID
    @Operation(summary = "Delete a situations list by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSituationsList(@PathVariable("id") int id) {
        if (!situationsListService.findSituationsListById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        situationsListService.deleteSituationsListById(id);
        return ResponseEntity.noContent().build();
    }

    // Получение списка сценариев по заданным id протокола и id паспорта станции
    @Operation(summary = "Get scenarios by protocol ID and station passport ID")
    @GetMapping("/protocol/{protocolId}/station-passport/{stationPassportId}")
    public ResponseEntity<List<SituationsList>> getScenariosByProtocolIdAndStationPassportId(@PathVariable int protocolId, @PathVariable int stationPassportId) {
        List<SituationsList> scenarios = situationsListService.findScenariosByProtocolIdAndStationPassportId(protocolId, stationPassportId);
        return ResponseEntity.ok(scenarios);
    }
}


//GET /api/situations-lists: Получение списка всех сценариев.
//GET /api/situations-lists/{id}: Получение сведений о конкретном сценарии по его ID.
//POST /api/situations-lists: Создание нового сценария.
//PUT /api/situations-lists/{id}: Обновление информации о существующем сценарии по его ID.
//DELETE /api/situations-lists/{id}: Удаление сценария по его ID.

//GET /api/situations-lists/protocol/{protocolId}/station-passport/{stationPassportId}:
// Получение списка сценариев по заданным id протокола и id паспорта станции.
