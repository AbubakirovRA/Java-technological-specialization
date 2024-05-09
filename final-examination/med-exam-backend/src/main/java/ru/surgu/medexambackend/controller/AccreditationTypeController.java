package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.AccreditationType;
import ru.surgu.medexambackend.service.AccreditationTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accreditation-types")
public class AccreditationTypeController {

    private final AccreditationTypeService accreditationTypeService;

    @Autowired
    public AccreditationTypeController(AccreditationTypeService accreditationTypeService) {
        this.accreditationTypeService = accreditationTypeService;
    }

    // Эндпоинт для сохранения нового типа аккредитации
    @Operation(summary = "Save a new accreditation type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Accreditation type saved"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<AccreditationType> saveAccreditationType(@RequestBody AccreditationType accreditationType) {
        AccreditationType savedAccreditationType = accreditationTypeService.saveAccreditationType(accreditationType);
        return new ResponseEntity<>(savedAccreditationType, HttpStatus.CREATED);
    }

    // Эндпоинт для получения всех типов аккредитации
    @Operation(summary = "Get all accreditation types")
    @GetMapping
    public ResponseEntity<List<AccreditationType>> getAllAccreditationTypes() {
        List<AccreditationType> accreditationTypes = accreditationTypeService.getAllAccreditationTypes();
        return ResponseEntity.ok(accreditationTypes);
    }

    // Эндпоинт для получения типа аккредитации по его ID
    @Operation(summary = "Get accreditation type by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the accreditation type"),
            @ApiResponse(responseCode = "404", description = "Accreditation type not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccreditationType> getAccreditationTypeById(@PathVariable int id) {
        Optional<AccreditationType> accreditationType = accreditationTypeService.getAccreditationTypeById(id);
        return accreditationType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Эндпоинт для удаления типа аккредитации по его ID
    @Operation(summary = "Delete accreditation type by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Accreditation type deleted"),
            @ApiResponse(responseCode = "404", description = "Accreditation type not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccreditationType(@PathVariable int id) {
        accreditationTypeService.deleteAccreditationType(id);
        return ResponseEntity.noContent().build();
    }
}


//POST /api/accreditation-types: Создание нового типа аккредитации.
// Принимает объект типа AccreditationType в теле запроса и сохраняет его в базе данных.
// Возвращает созданный объект типа AccreditationType и статус ответа 201 (Created).

//GET /api/accreditation-types: Получение всех типов аккредитации.
// Возвращает список всех типов аккредитации, сохраненных в базе данных, и статус ответа 200 (OK).

//GET /api/accreditation-types/{id}: Получение типа аккредитации по его ID.
// Принимает ID типа аккредитации в качестве переменной пути и возвращает объект типа AccreditationType с указанным ID,
// если такой существует. В случае отсутствия объекта возвращает статус ответа 404 (Not Found).

//DELETE /api/accreditation-types/{id}: Удаление типа аккредитации по его ID.
// Принимает ID типа аккредитации в качестве переменной пути и удаляет соответствующий объект из базы данных.
// Возвращает статус ответа 204 (No Content).