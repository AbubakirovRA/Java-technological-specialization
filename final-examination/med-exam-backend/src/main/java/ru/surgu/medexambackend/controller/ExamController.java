package ru.surgu.medexambackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.Exam;
import ru.surgu.medexambackend.entity.Protocol;
import ru.surgu.medexambackend.service.ExamService;

import java.util.List;

@RestController
@RequestMapping("/api/exams") // Базовый URL для всех запросов к контроллеру
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // Эндпоинт для сохранения экзамена
    @Operation(summary = "Сохранить экзамен")
    @PostMapping("/save")
    public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
        Exam savedExam = examService.saveExam(exam);
        return ResponseEntity.ok(savedExam);
    }

    // Эндпоинт для получения экзамена по его ID
    @Operation(summary = "Получить экзамен по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Экзамен найден"),
            @ApiResponse(responseCode = "404", description = "Экзамен не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable int id) {
        Exam exam = examService.getExamById(id);
        if (exam != null) {
            return ResponseEntity.ok(exam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Эндпоинт для получения всех экзаменов
    @Operation(summary = "Получить все экзамены")
    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    // Эндпоинт для удаления экзамена по его ID
    @Operation(summary = "Удалить экзамен по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable int id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    // Эндпоинт для получения протоколов для заданного экзамена
    @Operation(summary = "Получить протоколы для заданного экзамена")
    @GetMapping("/{examId}/protocols")
    public ResponseEntity<List<Protocol>> getProtocolsByExamId(@PathVariable int examId) {
        List<Protocol> protocols = examService.findProtocolsByExamId(examId);
        return ResponseEntity.ok(protocols);
    }

    // Эндпоинт для получения результатов для заданного экзамена
    @Operation(summary = "Получить результаты для заданного экзамена")
    @GetMapping("/{examId}/results")
    public ResponseEntity<List<Object[]>> getResultsByExamId(@PathVariable int examId) {
        List<Object[]> results = examService.findResultsByExamId(examId);
        return ResponseEntity.ok(results);
    }
}


//POST /api/exams/save: Этот эндпоинт используется для сохранения нового экзамена.
// Он принимает экземпляр экзамена в формате JSON в теле запроса и возвращает сохраненный экзамен.

//GET /api/exams/{id}: Этот эндпоинт позволяет получить экзамен по его уникальному идентификатору (ID).
// Он принимает ID экзамена в качестве переменной пути и возвращает экзамен в формате JSON.

//GET /api/exams/all: Этот эндпоинт возвращает список всех экзаменов.
// Он не принимает параметров и возвращает список всех доступных экзаменов в формате JSON.

//DELETE /api/exams/{id}: Этот эндпоинт используется для удаления экзамена по его ID.
// Он принимает ID экзамена в качестве переменной пути и не возвращает никаких данных.

//GET /api/exams/{examId}/protocols: Этот эндпоинт возвращает список протоколов, связанных с определенным экзаменом.
// Он принимает ID экзамена в качестве переменной пути и возвращает список протоколов в формате JSON.

//GET /api/exams/{examId}/results: Этот эндпоинт возвращает результаты, связанные с определенным экзаменом.
// Он принимает ID экзамена в качестве переменной пути и возвращает список результатов в формате JSON.

