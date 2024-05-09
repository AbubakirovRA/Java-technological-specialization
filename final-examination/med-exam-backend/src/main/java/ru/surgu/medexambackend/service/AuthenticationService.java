package ru.surgu.medexambackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.surgu.medexambackend.entity.Authentication;
import ru.surgu.medexambackend.repository.AuthenticationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    // Сохранение сущности Authentication
    public Authentication saveAuthentication(Authentication authentication) {
        return authenticationRepository.save(authentication);
    }

    // Получение сущности Authentication по ее идентификатору
    public Optional<Authentication> getAuthenticationById(int id) {
        return authenticationRepository.findById(id);
    }

    // Получение списка всех сущностей Authentication
    public List<Authentication> getAllAuthentications() {
        return authenticationRepository.findAll();
    }

    // Удаление сущности Authentication по ее идентификатору
    public void deleteAuthentication(int id) {
        authenticationRepository.deleteById(id);
    }
}
