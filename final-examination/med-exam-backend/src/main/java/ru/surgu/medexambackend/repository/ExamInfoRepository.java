package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.ExamInfo;

@Repository
public interface ExamInfoRepository extends JpaRepository<ExamInfo, Integer> {
//    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository
    // пользовательские методы (если будут добавлены в будущем) могут быть добавлены здесь
}
