package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.Specialization;
import ru.surgu.medexambackend.repository.SpecializationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    //Получение всех специализаций
         public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    //Получение специализации по ее ID
         public Optional<Specialization> getSpecializationById(int id) {
        return specializationRepository.findById(id);
    }

    //Сохранение специализации
    public Specialization saveSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    //Удаление специализации по ее ID
    public void deleteSpecializationById(int id) {
        specializationRepository.deleteById(id);
    }
}

