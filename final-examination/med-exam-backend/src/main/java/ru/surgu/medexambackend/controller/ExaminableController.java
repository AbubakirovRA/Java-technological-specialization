package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.*;
import ru.surgu.medexambackend.service.ExaminableService;

import java.util.List;

@RestController
@RequestMapping("/examinable")
public class ExaminableController {

    private final ExaminableService examinableService;

    @Autowired
    public ExaminableController(ExaminableService examinableService) {
        this.examinableService = examinableService;
    }

    // Эндпоинт для сохранения экземпляра Examinable
    @Operation(summary = "Save an Examinable")
    @PostMapping("/save")
    public ResponseEntity<Examinable> saveExaminable(@RequestBody Examinable examinable) {
        Examinable savedExaminable = examinableService.saveExaminable(examinable);
        return ResponseEntity.ok(savedExaminable);
    }

    // Эндпоинт для получения экземпляра Examinable по его идентификатору
    @Operation(summary = "Get an Examinable by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Examinable"),
            @ApiResponse(responseCode = "404", description = "Examinable not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Examinable> getExaminableById(@PathVariable int id) {
        Examinable examinable = examinableService.getExaminableById(id);
        if (examinable != null) {
            return ResponseEntity.ok(examinable);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Эндпоинт для получения списка всех экземпляров Examinable
    @Operation(summary = "Get all Examinables")
    @GetMapping("/all")
    public ResponseEntity<List<Examinable>> getAllExaminables() {
        List<Examinable> examinables = examinableService.getAllExaminables();
        return ResponseEntity.ok(examinables);
    }

    // Эндпоинт для удаления экземпляра Examinable по его идентификатору
    @Operation(summary = "Delete an Examinable by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExaminable(@PathVariable int id) {
        examinableService.deleteExaminable(id);
        return ResponseEntity.noContent().build();
    }

    // Эндпоинт для получения списка экзаменов для заданного экзаменуемого
    @Operation(summary = "Get Exams by Examinable ID")
    @GetMapping("/{examinableId}/exams")
    public ResponseEntity<List<Exam>> getExamsByExaminableId(@PathVariable int examinableId) {
        List<Exam> exams = examinableService.findExamsByExaminableId(examinableId);
        return ResponseEntity.ok(exams);
    }

    // Эндпоинт для получения списка протоколов для заданного экзаменуемого
    @Operation(summary = "Get Protocols by Examinable ID")
    @GetMapping("/{examinableId}/protocols")
    public ResponseEntity<List<Protocol>> getProtocolsByExaminableId(@PathVariable int examinableId) {
        List<Protocol> protocols = examinableService.findProtocolsByExaminableId(examinableId);
        return ResponseEntity.ok(protocols);
    }

    // Эндпоинт для получения списка паспортов станций для заданного экзаменуемого
    @Operation(summary = "Get Station Passports by Examinable ID")
    @GetMapping("/{examinableId}/station-passports")
    public ResponseEntity<List<StationPassport>> getStationPassportsByExaminableId(@PathVariable int examinableId) {
        List<StationPassport> stationPassports = examinableService.findStationPassportsByExaminableId(examinableId);
        return ResponseEntity.ok(stationPassports);
    }

    // Эндпоинт для получения списка сценариев для заданного экзаменуемого
    @Operation(summary = "Get Scenarios by Examinable ID")
    @GetMapping("/{examinableId}/scenarios")
    public ResponseEntity<List<Object[]>> getScenarioNumbersByExaminableId(@PathVariable int examinableId) {
        List<Object[]> scenarios = examinableService.findScenarioNumbersByExaminableId(examinableId);
        return ResponseEntity.ok(scenarios);
    }

    // Эндпоинт для получения списка действий для заданного экзаменуемого
    @Operation(summary = "Get Actions by Examinable ID")
    @GetMapping("/{examinableId}/actions")
    public ResponseEntity<List<String>> getActionsByExaminableId(@PathVariable int examinableId) {
        List<String> actions = examinableService.findActionsByExaminableId(examinableId);
        return ResponseEntity.ok(actions);
    }

    // Эндпоинт для получения списка экзаменуемых для конкретного экзамена
    @Operation(summary = "Get Examinees by Exam ID")
    @GetMapping("/exams/{examId}/examinees")
    public ResponseEntity<List<String>> getExamineesByExamId(@PathVariable int examId) {
        List<String> examinees = examinableService.findExamineesByExamId(examId);
        return ResponseEntity.ok(examinees);
    }
}


//Сохранение экземпляра Examinable:
//POST /examinable/save
//Принимает POST-запрос с телом, содержащим экземпляр Examinable, сохраняет его в базе данных и возвращает сохраненный экземпляр.

//Получение экземпляра Examinable по его идентификатору:
//GET /examinable/{id}
//Принимает GET-запрос с идентификатором экзаменуемого в качестве пути,
// возвращает экземпляр Examinable с соответствующим идентификатором,
// если он существует.

//Получение списка всех экземпляров Examinable:
//GET /examinable/all
//Возвращает список всех экземпляров Examinable.

//Удаление экземпляра Examinable по его идентификатору:
//DELETE /examinable/{id}
//Принимает DELETE-запрос с идентификатором экзаменуемого в качестве пути,
//удаляет соответствующий экземпляр из базы данных.

//Получение списка экзаменов для заданного экзаменуемого:
//GET /examinable/{examinableId}/exams
//Возвращает список экзаменов для заданного экзаменуемого.

//Получение списка протоколов для заданного экзаменуемого:
//GET /examinable/{examinableId}/protocols
//Возвращает список протоколов для заданного экзаменуемого.

//Получение списка паспортов станций для заданного экзаменуемого:
//GET /examinable/{examinableId}/station-passports
//Возвращает список паспортов станций для заданного экзаменуемого.

//Получение списка сценариев для заданного экзаменуемого:
//GET /examinable/{examinableId}/scenarios
//Возвращает список сценариев для заданного экзаменуемого.

//Получение списка действий для заданного экзаменуемого:
//GET /examinable/{examinableId}/actions
//Возвращает список действий для заданного экзаменуемого.

//Получение списка экзаменуемых для конкретного экзамена:
//GET /examinable/exams/{examId}/examinees
//Возвращает список экзаменуемых для конкретного экзамена.


