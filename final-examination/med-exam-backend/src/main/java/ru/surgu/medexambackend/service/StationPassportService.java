package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.StationPassport;
import ru.surgu.medexambackend.repository.StationPassportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StationPassportService {

    private final StationPassportRepository stationPassportRepository;

    @Autowired
    public StationPassportService(StationPassportRepository stationPassportRepository) {
        this.stationPassportRepository = stationPassportRepository;
    }

    // Стандартный метод для поиска всех паспортов станций
    public List<StationPassport> findAll() {
        return stationPassportRepository.findAll();
    }

    // Стандартный метод для поиска паспорта станции по его ID
    public Optional<StationPassport> findById(int id) {
        return stationPassportRepository.findById(id);
    }

    // Стандартный метод для сохранения паспорта станции
    public StationPassport save(StationPassport stationPassport) {
        return stationPassportRepository.save(stationPassport);
    }

    // Стандартный метод для удаления паспорта станции по его ID
    public void deleteById(int id) {
        stationPassportRepository.deleteById(id);
    }

    // Дополнительные методы, если такие есть в репозитории, могут быть добавлены здесь
}

