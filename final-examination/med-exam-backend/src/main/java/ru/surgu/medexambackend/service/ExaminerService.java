package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.Examiner;
import ru.surgu.medexambackend.repository.ExaminerRepository;

import java.util.List;

@Service
public class ExaminerService {

    private final ExaminerRepository examinerRepository;

    @Autowired
    public ExaminerService(ExaminerRepository examinerRepository) {
        this.examinerRepository = examinerRepository;
    }

    // Сохранение экземпляра Examiner
    public Examiner saveExaminer(Examiner examiner) {
        return examinerRepository.save(examiner);
    }

    // Получение экземпляра Examiner по его идентификатору
    public Examiner getExaminerById(int id) {
        return examinerRepository.findById(id).orElse(null);
    }

    // Получение списка всех экземпляров Examiner
    public List<Examiner> getAllExaminers() {
        return examinerRepository.findAll();
    }

    // Удаление экземпляра Examiner по его идентификатору
    public void deleteExaminer(int id) {
        examinerRepository.deleteById(id);
    }

    // Получение списка экзаменов, проведенных конкретным экзаменатором
    public List<Examiner> findExaminersByProtocolsIsNotNull() {
        return examinerRepository.findExaminersByProtocolsIsNotNull();
    }
}
