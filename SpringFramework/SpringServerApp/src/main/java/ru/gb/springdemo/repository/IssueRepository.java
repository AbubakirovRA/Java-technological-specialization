package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        // Добавление выдачи в репозиторий
        issues.add(issue);
    }

    public List<Issue> getIssuesByReaderId(Long readerId) {
        // Получение списка всех выдач по читателю
        return issues.stream()
                .filter(issue -> issue.getReaderId().equals(readerId))
                .collect(Collectors.toList());
    }

    public List<Issue> getActiveIssuesByReaderId(Long readerId) {
        // Получение списка активных выдач по читателю (не возвращенных)
        return issues.stream()
                .filter(issue -> issue.getReaderId().equals(readerId) && issue.getReturnedAt() == null)
                .collect(Collectors.toList());
    }

    public Issue getIssueById(Long issueId) {
        // Получение выдачи по идентификатору
        return issues.stream()
                .filter(issue -> issue.getId().equals(issueId))
                .findFirst()
                .orElse(null);
    }

    // Дополнение: Метод для установки даты возврата книги при закрытии выдачи
    public void closeIssue(Long issueId) {
        Issue issue = getIssueById(issueId);
        if (issue != null) {
            // Установка даты возврата при закрытии выдачи
            issue.setReturnedAt();
        }
    }
}
