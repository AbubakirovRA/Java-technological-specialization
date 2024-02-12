package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.List;

// Интерфейс IssueRepository
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> getAllIssues();

    Issue getIssueById(Long id);

    void closeIssue(Long issueId);
    // Возможно, в дальнейшем потребуются дополнительные методы, но пока достаточно предоставляемых JpaRepository
}
