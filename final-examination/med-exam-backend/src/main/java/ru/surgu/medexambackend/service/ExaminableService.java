package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.Examinable;
import ru.surgu.medexambackend.entity.Exam;
import ru.surgu.medexambackend.entity.Protocol;
import ru.surgu.medexambackend.entity.StationPassport;
import ru.surgu.medexambackend.repository.ExaminableRepository;

import java.util.List;

@Service
public class ExaminableService {

    private final ExaminableRepository examinableRepository;

    @Autowired
    public ExaminableService(ExaminableRepository examinableRepository) {
        this.examinableRepository = examinableRepository;
    }

    // Сохранение экземпляра Examinable
    public Examinable saveExaminable(Examinable examinable) {
        return examinableRepository.save(examinable);
    }

    // Получение экземпляра Examinable по его идентификатору
    public Examinable getExaminableById(int id) {
        return examinableRepository.findById(id).orElse(null);
    }

    // Получение списка всех экземпляров Examinable
    public List<Examinable> getAllExaminables() {
        return examinableRepository.findAll();
    }

    // Удаление экземпляра Examinable по его идентификатору
    public void deleteExaminable(int id) {
        examinableRepository.deleteById(id);
    }

    // Получение списка экзаменов для заданного экзаменуемого
    public List<Exam> findExamsByExaminableId(int examinableId) {
        return examinableRepository.findExamsByExaminableId(examinableId);
    }

    // Получение списка протоколов для заданного экзаменуемого
    public List<Protocol> findProtocolsByExaminableId(int examinableId) {
        return examinableRepository.findProtocolsByExaminableId(examinableId);
    }

    // Получение списка паспортов станций для заданного экзаменуемого
    public List<StationPassport> findStationPassportsByExaminableId(int examinableId) {
        return examinableRepository.findStationPassportsByExaminableId(examinableId);
    }

    // Получение списка сценариев для заданного экзаменуемого
    public List<Object[]> findScenarioNumbersByExaminableId(int examinableId) {
        return examinableRepository.findScenarioNumbersByExaminableId(examinableId);
    }

    // Получение списка действий для заданного экзаменуемого
    public List<String> findActionsByExaminableId(int examinableId) {
        return examinableRepository.findActionsByExaminableId(examinableId);
    }

    // Получение списка экзаменуемых для конкретного экзамена
    public List<String> findExamineesByExamId(int examId) {
        return examinableRepository.findExamineesByExamId(examId);
    }
}


