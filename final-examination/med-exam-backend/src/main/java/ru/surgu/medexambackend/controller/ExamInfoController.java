package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.ExamInfo;
import ru.surgu.medexambackend.service.ExamInfoService;

import java.util.List;

@RestController
@RequestMapping("/exam-info")
public class ExamInfoController {

    private final ExamInfoService examInfoService;

    @Autowired
    public ExamInfoController(ExamInfoService examInfoService) {
        this.examInfoService = examInfoService;
    }

    // Эндпоинт для сохранения информации о экзамене
    @Operation(summary = "Save exam information")
    @PostMapping("/save")
    public ResponseEntity<ExamInfo> saveExamInfo(@RequestBody ExamInfo examInfo) {
        ExamInfo savedExamInfo = examInfoService.saveExamInfo(examInfo);
        return ResponseEntity.ok(savedExamInfo);
    }

    // Эндпоинт для получения информации о экзамене по его ID
    @Operation(summary = "Get exam information by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the exam information", content = @Content(schema = @Schema(implementation = ExamInfo.class))),
            @ApiResponse(responseCode = "404", description = "Exam information not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExamInfo> getExamInfoById(@PathVariable int id) {
        ExamInfo examInfo = examInfoService.getExamInfoById(id);
        if (examInfo != null) {
            return ResponseEntity.ok(examInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Эндпоинт для получения списка всех записей о экзамене
    @Operation(summary = "Get all exam information")
    @GetMapping("/all")
    public ResponseEntity<List<ExamInfo>> getAllExamInfos() {
        List<ExamInfo> examInfos = examInfoService.getAllExamInfos();
        return ResponseEntity.ok(examInfos);
    }

    // Эндпоинт для удаления информации о экзамене по его ID
    @Operation(summary = "Delete exam information by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamInfo(@PathVariable int id) {
        examInfoService.deleteExamInfo(id);
        return ResponseEntity.noContent().build();
    }
}


//Сохранение информации о экзамене:
//Метод: POST
//Путь: /exam-info/save
//Принимаемые данные: объект ExamInfo в теле запроса
//Ответ: сохраненный объект ExamInfo

//Получение информации о экзамене по его ID:
//Метод: GET
//Путь: /exam-info/{id}
//Параметры: id - идентификатор экзамена
//Ответ: информация о экзамене с указанным id

//Получение списка всех записей о экзаменах:
//Метод: GET
//Путь: /exam-info/all
//Ответ: список всех записей о экзаменах

//Удаление информации о экзамене по его ID:
//Метод: DELETE
//Путь: /exam-info/{id}
//Параметры: id - идентификатор экзамена
//Ответ: статус ответа без содержимого (204 No Content)