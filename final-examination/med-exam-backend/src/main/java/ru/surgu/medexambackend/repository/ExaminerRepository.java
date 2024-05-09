package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.Examiner;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {

//    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository

    // Получение списка экзаменов, проведенных конкретным экзаменатором
    List<Examiner> findExaminersByProtocolsIsNotNull();

    // Получение списка всех экзаменаторов
    List<Examiner> findAll();
}

