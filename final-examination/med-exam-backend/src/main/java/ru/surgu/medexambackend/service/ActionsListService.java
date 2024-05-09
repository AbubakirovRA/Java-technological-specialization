package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.ActionsList;
import ru.surgu.medexambackend.repository.ActionsListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActionsListService {

    private final ActionsListRepository actionsListRepository;

    @Autowired
    public ActionsListService(ActionsListRepository actionsListRepository) {
        this.actionsListRepository = actionsListRepository;
    }

    // Сохранение действия
    public ActionsList saveAction(ActionsList action) {
        return actionsListRepository.save(action);
    }

    // Получение действия по его ID
    public ActionsList getActionById(int id) {
        Optional<ActionsList> optionalAction = actionsListRepository.findById(id);
        return optionalAction.orElse(null);
    }

    // Получение всех действий
    public List<ActionsList> getAllActions() {
        return actionsListRepository.findAll();
    }

    // Удаление действия по его ID
    public void deleteAction(int id) {
        actionsListRepository.deleteById(id);
    }
}

