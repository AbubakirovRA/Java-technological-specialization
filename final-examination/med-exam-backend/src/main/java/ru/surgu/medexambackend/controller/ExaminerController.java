package ru.surgu.medexambackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.surgu.medexambackend.entity.Examiner;
import ru.surgu.medexambackend.repository.ExaminerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExaminerController {

    private final ExaminerRepository examinerRepository;

    @Autowired
    public ExaminerController(ExaminerRepository examinerRepository) {
        this.examinerRepository = examinerRepository;
    }

    // Получение всех экзаменаторов
    @Operation(summary = "Get all examiners")
    @GetMapping("/examiners")
    public ResponseEntity<List<Examiner>> getAllExaminers() {
        try {
            List<Examiner> examiners = examinerRepository.findAll();
            if (examiners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(examiners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Получение экзаменатора по его ID
    @Operation(summary = "Get examiner by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the examiner"),
            @ApiResponse(responseCode = "404", description = "Examiner not found")
    })
    @GetMapping("/examiners/{id}")
    public ResponseEntity<Examiner> getExaminerById(@PathVariable("id") int id) {
        Optional<Examiner> optionalExaminer = examinerRepository.findById(id);
        return optionalExaminer.map(examiner -> new ResponseEntity<>(examiner, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Получение списка экзаменаторов, проведенных как минимум одним экзаменом
    @Operation(summary = "Get examiners with protocols")
    @GetMapping("/examiners/with-protocols")
    public ResponseEntity<List<Examiner>> getExaminersWithProtocols() {
        try {
            List<Examiner> examiners = examinerRepository.findExaminersByProtocolsIsNotNull();
            if (examiners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(examiners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Создание нового экзаменатора
    @Operation(summary = "Create a new examiner")
    @PostMapping("/examiners")
    public ResponseEntity<Examiner> createExaminer(@RequestBody Examiner examiner) {
        try {
            Examiner createdExaminer = examinerRepository.save(examiner);
            return new ResponseEntity<>(createdExaminer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Обновление информации об экзаменаторе по его ID
    @Operation(summary = "Update examiner by ID")
    @PutMapping("/examiners/{id}")
    public ResponseEntity<Examiner> updateExaminer(@PathVariable("id") int id, @RequestBody Examiner examinerDetails) {
        try {
            Optional<Examiner> optionalExaminer = examinerRepository.findById(id);
            if (optionalExaminer.isPresent()) {
                Examiner updatedExaminer = optionalExaminer.get();
                updatedExaminer.setFullName(examinerDetails.getFullName());
                updatedExaminer.setMedicalSpecialty(examinerDetails.getMedicalSpecialty());
                return new ResponseEntity<>(examinerRepository.save(updatedExaminer), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Удаление экзаменатора по его ID
    @Operation(summary = "Delete examiner by ID")
    @DeleteMapping("/examiners/{id}")
    public ResponseEntity<HttpStatus> deleteExaminer(@PathVariable("id") int id) {
        try {
            examinerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


//GET /api/examiners - Получение списка всех экзаменаторов.
//GET /api/examiners/{id} - Получение информации об экзаменаторе по его ID.
//GET /api/examiners/with-protocols - Получение списка экзаменаторов, проведенных как минимум одним экзаменом.
//POST /api/examiners - Создание нового экзаменатора.
//PUT /api/examiners/{id} - Обновление информации об экзаменаторе по его ID.
//DELETE /api/examiners/{id} - Удаление экзаменатора по его ID.
