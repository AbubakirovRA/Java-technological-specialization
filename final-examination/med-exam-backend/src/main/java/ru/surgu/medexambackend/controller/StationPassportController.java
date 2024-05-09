package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.StationPassport;
import ru.surgu.medexambackend.service.StationPassportService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/station-passports")
public class StationPassportController {

    private final StationPassportService stationPassportService;

    @Autowired
    public StationPassportController(StationPassportService stationPassportService) {
        this.stationPassportService = stationPassportService;
    }

    // Обработка запроса на получение всех паспортов станций
    @Operation(summary = "Get all station passports")
    @GetMapping
    public ResponseEntity<List<StationPassport>> getAllStationPassports() {
        List<StationPassport> stationPassports = stationPassportService.findAll();
        return ResponseEntity.ok(stationPassports);
    }

    // Обработка запроса на получение паспорта станции по его ID
    @Operation(summary = "Get a station passport by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the station passport"),
            @ApiResponse(responseCode = "404", description = "Station passport not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StationPassport> getStationPassportById(@PathVariable("id") int id) {
        Optional<StationPassport> stationPassportOptional = stationPassportService.findById(id);
        return stationPassportOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Обработка запроса на создание нового паспорта станции
    @Operation(summary = "Create a new station passport")
    @PostMapping
    public ResponseEntity<StationPassport> createStationPassport(@RequestBody StationPassport stationPassport) {
        StationPassport savedStationPassport = stationPassportService.save(stationPassport);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStationPassport);
    }

    // Обработка запроса на удаление паспорта станции по его ID
    @Operation(summary = "Delete a station passport by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Station passport deleted"),
            @ApiResponse(responseCode = "404", description = "Station passport not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStationPassport(@PathVariable("id") int id) {
        stationPassportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


//GET /api/station-passports - Получение списка всех паспортов станций.
//GET /api/station-passports/{id} - Получение информации о конкретном паспорте станции по его идентификатору.
//POST /api/station-passports - Создание нового паспорта станции.
//DELETE /api/station-passports/{id} - Удаление паспорта станции по его идентификатору.
