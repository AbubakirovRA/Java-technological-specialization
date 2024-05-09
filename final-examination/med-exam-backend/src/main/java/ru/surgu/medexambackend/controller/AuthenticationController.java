package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.Authentication;
import ru.surgu.medexambackend.service.AuthenticationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Создание новой сущности Authentication
    @Operation(summary = "Create a new Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Authentication created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping("/create")
    public ResponseEntity<Authentication> createAuthentication(@RequestBody Authentication authentication) {
        Authentication createdAuthentication = authenticationService.saveAuthentication(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthentication);
    }

    // Получение сущности Authentication по ее ID
    @Operation(summary = "Get Authentication by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Authentication"),
            @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Authentication> getAuthenticationById(@PathVariable int id) {
        Optional<Authentication> authentication = authenticationService.getAuthenticationById(id);
        return authentication.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Получение списка всех сущностей Authentication
    @Operation(summary = "Get all Authentications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Authentications"),
            @ApiResponse(responseCode = "204", description = "No Authentications found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Authentication>> getAllAuthentications() {
        List<Authentication> authentications = authenticationService.getAllAuthentications();
        if (authentications.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(authentications);
        }
    }

    // Удаление сущности Authentication по ее ID
    @Operation(summary = "Delete Authentication by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Authentication deleted"),
            @ApiResponse(responseCode = "404", description = "Authentication not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthentication(@PathVariable int id) {
        authenticationService.deleteAuthentication(id);
        return ResponseEntity.noContent().build();
    }
}


//POST /api/authentication/create: Создание новой сущности Authentication.
//GET /api/authentication/{id}: Получение сущности Authentication по ее ID.
//GET /api/authentication/all: Получение списка всех сущностей Authentication.
//DELETE /api/authentication/{id}: Удаление сущности Authentication по ее ID.
