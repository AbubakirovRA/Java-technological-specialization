package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.Exam;
import ru.surgu.medexambackend.entity.Protocol;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    //    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository

    // Получение информации о том, какие протоколы содержит данный экзамен
    @Query("SELECT e.protocolId FROM exam e WHERE e.id = :examId")
    List<Protocol> findProtocolsByExamId(@Param("examId") int examId);

    // Получение информации о том, какие результаты содержатся в протоколах, входящих в данный экзамен
    @Query("SELECT ei.result, ei.actionId FROM exam e JOIN exam_info ei ON e.protocolId.id = ei.protocolId.id WHERE e.id = :examId")
    List<Object[]> findResultsByExamId(@Param("examId") int examId);
}
