package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.ExamInfo;
import ru.surgu.medexambackend.repository.ExamInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamInfoService {

    private final ExamInfoRepository examInfoRepository;

    @Autowired
    public ExamInfoService(ExamInfoRepository examInfoRepository) {
        this.examInfoRepository = examInfoRepository;
    }

    // Сохранение информации о экзамене
    public ExamInfo saveExamInfo(ExamInfo examInfo) {
        return examInfoRepository.save(examInfo);
    }

    // Получение информации о экзамене по его ID
    public ExamInfo getExamInfoById(int id) {
        Optional<ExamInfo> optionalExamInfo = examInfoRepository.findById(id);
        return optionalExamInfo.orElse(null);
    }

    // Получение списка всех записей о экзамене
    public List<ExamInfo> getAllExamInfos() {
        return examInfoRepository.findAll();
    }

    // Удаление информации о экзамене по его ID
    public void deleteExamInfo(int id) {
        examInfoRepository.deleteById(id);
    }
}

