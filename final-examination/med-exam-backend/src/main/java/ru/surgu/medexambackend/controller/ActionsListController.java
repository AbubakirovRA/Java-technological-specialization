package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.ActionsList;
import ru.surgu.medexambackend.service.ActionsListService;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
public class ActionsListController {

    private final ActionsListService actionsListService;

    @Autowired
    public ActionsListController(ActionsListService actionsListService) {
        this.actionsListService = actionsListService;
    }

    // Создание нового действия
    @Operation(summary = "Create a new action")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Action created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<ActionsList> createAction(@RequestBody ActionsList action) {
        ActionsList createdAction = actionsListService.saveAction(action);
        return new ResponseEntity<>(createdAction, HttpStatus.CREATED);
    }

    // Получение действия по его ID
    @Operation(summary = "Get action by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the action"),
            @ApiResponse(responseCode = "404", description = "Action not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ActionsList> getActionById(@PathVariable("id") int id) {
        ActionsList action = actionsListService.getActionById(id);
        if (action != null) {
            return new ResponseEntity<>(action, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получение всех действий
    @Operation(summary = "Get all actions")
    @GetMapping("/all")
    public ResponseEntity<List<ActionsList>> getAllActions() {
        List<ActionsList> actions = actionsListService.getAllActions();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    // Удаление действия по его ID
    @Operation(summary = "Delete action by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Action deleted"),
            @ApiResponse(responseCode = "404", description = "Action not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAction(@PathVariable("id") int id) {
        actionsListService.deleteAction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


//POST /api/actions/create: Создание нового действия.
//GET /api/actions/{id}: Получение действия по его ID.
//GET /api/actions/all: Получение всех действий.
//DELETE /api/actions/{id}: Удаление действия по его ID.