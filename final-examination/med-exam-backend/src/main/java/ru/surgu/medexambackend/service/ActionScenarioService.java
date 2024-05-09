package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.ActionScenario;
import ru.surgu.medexambackend.repository.ActionScenarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActionScenarioService {

    private final ActionScenarioRepository actionScenarioRepository;

    @Autowired
    public ActionScenarioService(ActionScenarioRepository actionScenarioRepository) {
        this.actionScenarioRepository = actionScenarioRepository;
    }

    // Сохранение экземпляра ActionScenario
    public ActionScenario saveActionScenario(ActionScenario actionScenario) {
        return actionScenarioRepository.save(actionScenario);
    }

    // Получение экземпляра ActionScenario по его идентификатору
    public ActionScenario getActionScenarioById(int id) {
        Optional<ActionScenario> optionalActionScenario = actionScenarioRepository.findById(id);
        return optionalActionScenario.orElse(null);
    }

    // Получение списка всех экземпляров ActionScenario
    public List<ActionScenario> getAllActionScenarios() {
        return actionScenarioRepository.findAll();
    }

    // Удаление экземпляра ActionScenario по его идентификатору
    public void deleteActionScenario(int id) {
        actionScenarioRepository.deleteById(id);
    }
}

