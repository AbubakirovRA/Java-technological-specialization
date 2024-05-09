package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.Exam;
import ru.surgu.medexambackend.entity.Protocol;
import ru.surgu.medexambackend.repository.ExamRepository;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    // Стандартный метод сохранения экзамена
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    // Стандартный метод поиска экзамена по его ID
    public Exam getExamById(int id) {
        return examRepository.findById(id).orElse(null);
    }

    // Стандартный метод получения всех экзаменов
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // Стандартный метод удаления экзамена по его ID
    public void deleteExam(int id) {
        examRepository.deleteById(id);
    }

    // Получение протоколов для заданного экзамена
    public List<Protocol> findProtocolsByExamId(int examId) {
        return examRepository.findProtocolsByExamId(examId);
    }

    // Получение результатов для заданного экзамена
    public List<Object[]> findResultsByExamId(int examId) {
        return examRepository.findResultsByExamId(examId);
    }
}

