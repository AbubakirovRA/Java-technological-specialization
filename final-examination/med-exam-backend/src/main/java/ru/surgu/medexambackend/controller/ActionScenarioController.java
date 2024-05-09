package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.ActionScenario;
import ru.surgu.medexambackend.service.ActionScenarioService;

import java.util.List;

@RestController
@RequestMapping("/api/action-scenarios")
public class ActionScenarioController {

    private final ActionScenarioService actionScenarioService;

    @Autowired
    public ActionScenarioController(ActionScenarioService actionScenarioService) {
        this.actionScenarioService = actionScenarioService;
    }

    // Создание нового экземпляра ActionScenario
    @Operation(summary = "Create a new ActionScenario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ActionScenario created"),
            @ApiResponse(responseCode = "400", description = "Invalid ActionScenario request")
    })
    @PostMapping
    public ResponseEntity<ActionScenario> createActionScenario(@RequestBody ActionScenario actionScenario) {
        ActionScenario createdActionScenario = actionScenarioService.saveActionScenario(actionScenario);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActionScenario);
    }

    // Получение экземпляра ActionScenario по его ID
    @Operation(summary = "Get an ActionScenario by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ActionScenario"),
            @ApiResponse(responseCode = "404", description = "ActionScenario not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ActionScenario> getActionScenarioById(@PathVariable int id) {
        ActionScenario actionScenario = actionScenarioService.getActionScenarioById(id);
        if (actionScenario != null) {
            return ResponseEntity.ok(actionScenario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Получение списка всех экземпляров ActionScenario
    @Operation(summary = "Get all ActionScenarios")
    @GetMapping
    public ResponseEntity<List<ActionScenario>> getAllActionScenarios() {
        List<ActionScenario> actionScenarios = actionScenarioService.getAllActionScenarios();
        return ResponseEntity.ok(actionScenarios);
    }

    // Удаление экземпляра ActionScenario по его ID
    @Operation(summary = "Delete an ActionScenario by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ActionScenario deleted"),
            @ApiResponse(responseCode = "404", description = "ActionScenario not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActionScenario(@PathVariable int id) {
        actionScenarioService.deleteActionScenario(id);
        return ResponseEntity.noContent().build();
    }
}


//POST /api/action-scenarios: Создает новый экземпляр ActionScenario.
//GET /api/action-scenarios/{id}: Получает экземпляр ActionScenario по его ID.
//GET /api/action-scenarios: Получает список всех экземпляров ActionScenario.
//DELETE /api/action-scenarios/{id}: Удаляет экземпляр ActionScenario по его ID.
