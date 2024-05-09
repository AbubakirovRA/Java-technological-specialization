package ru.surgu.medexambackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.surgu.medexambackend.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    //    методы для стандартных операций CRUD предоставляются по умолчанию через интерфейс JpaRepository
    // пользовательские методы (если будут добавлены в будущем) могут быть добавлены здесь
}

