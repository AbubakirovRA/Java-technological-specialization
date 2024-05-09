package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.AccreditationType;
import ru.surgu.medexambackend.repository.AccreditationTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccreditationTypeService {

    private final AccreditationTypeRepository accreditationTypeRepository;

    @Autowired
    public AccreditationTypeService(AccreditationTypeRepository accreditationTypeRepository) {
        this.accreditationTypeRepository = accreditationTypeRepository;
    }

    // Сохранение нового типа аккредитации
    public AccreditationType saveAccreditationType(AccreditationType accreditationType) {
        return accreditationTypeRepository.save(accreditationType);
    }

    // Получение типа аккредитации по его ID
    public Optional<AccreditationType> getAccreditationTypeById(int id) {
        return accreditationTypeRepository.findById(id);
    }

    // Получение всех типов аккредитации
    public List<AccreditationType> getAllAccreditationTypes() {
        return accreditationTypeRepository.findAll();
    }

    // Удаление типа аккредитации по его ID
    public void deleteAccreditationType(int id) {
        accreditationTypeRepository.deleteById(id);
    }
}

