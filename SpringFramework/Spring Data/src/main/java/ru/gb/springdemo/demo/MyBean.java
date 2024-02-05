package ru.gb.springdemo.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBean {

    @Value("${application.issue.max-allowed-books:1}")
    private int maxAllowedBooks;

    public MyBean(MyService myService) {
        log.info("constructor");
    }

    @Autowired
    public void setMyService(MyService myService) {
        log.info("setter-injection");
        // this.myService = myService
    }

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct");
    }

    @EventListener(DatabaseConnectionSetupEvent.class)
    public void onDatabaseConnectionSetup() {
        log.info("onDatabaseConnectionSetup");

        log.info("property = {}", maxAllowedBooks);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("preDestroy");
    }

    // Геттер для параметра max-allowed-books
    public int getMaxAllowedBooks() {
        return maxAllowedBooks;
    }

    // Метод для использования параметра max-allowed-books в проверке наличия книг у читателя
    public boolean isBooksLimitExceeded(int currentBooksCount) {
        return currentBooksCount >= maxAllowedBooks;
    }
}
