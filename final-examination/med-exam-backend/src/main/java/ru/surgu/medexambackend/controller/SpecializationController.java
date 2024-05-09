package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.Specialization;
import ru.surgu.medexambackend.service.SpecializationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    // Эндпоинт для получения всех специализаций
    @Operation(summary = "Get all specializations")
    @GetMapping
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        List<Specialization> specializations = specializationService.getAllSpecializations();
        return ResponseEntity.ok(specializations);
    }

    // Эндпоинт для получения специализации по ID
    @Operation(summary = "Get specialization by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the specialization"),
            @ApiResponse(responseCode = "404", description = "Specialization not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable int id) {
        Optional<Specialization> specialization = specializationService.getSpecializationById(id);
        return specialization.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Эндпоинт для сохранения специализации
    @Operation(summary = "Save specialization")
    @PostMapping
    public ResponseEntity<Specialization> saveSpecialization(@RequestBody Specialization specialization) {
        Specialization savedSpecialization = specializationService.saveSpecialization(specialization);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpecialization);
    }

    // Эндпоинт для удаления специализации по ID
    @Operation(summary = "Delete specialization by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Specialization deleted"),
            @ApiResponse(responseCode = "404", description = "Specialization not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecializationById(@PathVariable int id) {
        specializationService.deleteSpecializationById(id);
        return ResponseEntity.noContent().build();
    }
}


//GET /specializations - возвращает все специализации.
//GET /specializations/{id} - возвращает специализацию по ее ID.
//POST /specializations - сохраняет новую специализацию.
//DELETE /specializations/{id} - удаляет специализацию по ее ID.
