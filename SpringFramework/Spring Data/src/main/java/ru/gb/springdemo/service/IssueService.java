package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    @Value("${application.issue.max-allowed-books:1}")
    private int maxAllowedBooks;

    public Issue issue(IssueRequest request) {
        validateBookAndReader(request.getBookId(), request.getReaderId());

        if (getActiveIssuesByReaderId(request.getReaderId()).size() >= maxAllowedBooks) {
            throw new IllegalStateException("Превышено максимальное количество книг у пользователя");
        }

        // Изменено создание объекта Issue
        Issue issue = new Issue();
        issue.setBook(new Book(request.getBookId())); // Предполагаем, что у Book есть конструктор с параметром id
        issue.setReader(new Reader(request.getReaderId())); // Предполагаем, что у Reader есть конструктор с параметром id
        issueRepository.save(issue);
        return issue;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    // Остальные методы без изменений...
}
